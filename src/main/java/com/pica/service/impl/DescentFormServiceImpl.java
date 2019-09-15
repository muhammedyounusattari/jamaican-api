package com.pica.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.MongoWriteException;
import com.pica.commons.FormStatus;
import com.pica.commons.PICAApplictions;
import com.pica.config.PicaStringGenerator;
import com.pica.dao.AgentDAO;
import com.pica.dao.AllFormsDAO;
import com.pica.dao.CreateProfileDAO;
import com.pica.dao.DescentFormDAO;
import com.pica.dao.DeskClerkDAO;
import com.pica.dao.NaturalizationAliensDAO;
import com.pica.dao.RolesDAO;
import com.pica.dao.SequenceDAO;
import com.pica.dao.SupervisorDAO;
import com.pica.mapper.AdminReviewFormMapper;
import com.pica.mapper.DescentFormHandler;
import com.pica.mapper.DescentFormMapper;
import com.pica.mapper.EmailMessageTemplate;
import com.pica.mapper.PICAMapper;
import com.pica.model.Agent;
import com.pica.model.AllForms;
import com.pica.model.Applicant;
import com.pica.model.ApplicantDocument;
import com.pica.model.Application;
import com.pica.model.DescentForm;
import com.pica.model.DeskClerk;
import com.pica.model.Profile;
import com.pica.model.Roles;
import com.pica.model.Supervisor;
import com.pica.model.natural.alien.NaturalizationAliens;
import com.pica.payloads.AssignedApplicationPayload;
import com.pica.service.DescentFormService;

import pica.notification.config.NotificationConfig;

@Service
public class DescentFormServiceImpl implements DescentFormService {

	private static final Logger LOG = LoggerFactory.getLogger(DescentFormServiceImpl.class);

	@Autowired
	private CreateProfileDAO profileDAO;

	@Autowired
	private DescentFormDAO descentFormDAO;

	@Autowired
	private AllFormsDAO allFormsDAO;

	@Autowired
	private SequenceDAO sequenceDAO;

	@Autowired
	private NotificationConfig notificationConfig;

	@Autowired
	private SupervisorDAO supervisorDAO;

	@Autowired
	private AgentDAO agentDAO;
	
	@Autowired
	private DeskClerkDAO deskClerkDAO;
	
	@Autowired
	private RolesDAO rolesDAO;
	
	@Autowired
	private NaturalizationAliensDAO naturalizationAliensDAO;

	//@Value("${email.enabled}")
	private String isEmailEnabled="false";;

	@Override
	public List<AllForms> getAllPicaForms() {
		List<AllForms> allForms = allFormsDAO.findAll();
		return allForms;
	}

	@Value("${create.profile.subject}")
	private String subject;

	@Value("${create.profile.message}")
	private String message;

	@Value("${create.profile.message}")
	private String from;

	private String HOSTING = "hosting";

	@Override
	public Profile createPicaProfile(Profile profile) {
		profile.setId((int) sequenceDAO.getNextSequenceId(HOSTING));
		profile.setPassword(PicaStringGenerator.generatePassword());

		try {
			if(profileDAO.findByEmail(profile.getEmail())!=null) {
				return null;
			}
			profile = profileDAO.save(profile);
			message = EmailMessageTemplate.getProfileMessageTemplate(profile);

			if (isEmailEnabled.equalsIgnoreCase("true"))
				notificationConfig.sendMail(new String[] { profile.getEmail() }, subject, message, "dummy");
		} catch (MongoWriteException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return profile;
	}

	@Override
	public Profile authenticateUser(Profile createProfile) {
		Profile profile = profileDAO.findByEmailAndPassword(createProfile.getEmail(),
				createProfile.getPassword());
		return profile;
	}

	@Override
	public DescentForm submitDescentApplication(DescentFormHandler descentFormHandler) {
		DescentForm descentForm = DescentFormMapper.formatPayload(descentFormHandler);
		DescentForm descentFormDb = descentFormDAO.findByProfileEmail(descentForm.getProfile().getEmail());

		Profile profileDb = profileDAO.findByEmail(descentForm.getProfile().getEmail());
		profileDb = DescentFormMapper.syncProfileForm(descentForm.getProfile(), profileDb);

		if (descentFormDb != null) {
			descentFormDb.setProfile(profileDb);
			descentForm = DescentFormMapper.syncDescentForm(descentForm, descentFormDb);
			// descentForm.getProfile().setPassword(descentFormHandler.getPassword());
			profileDAO.save(descentForm.getProfile());
			return descentFormDAO.save(descentForm);
		}

		descentForm.setId((int) sequenceDAO.getNextSequenceIdProfile(HOSTING));
		// profileDb.setPassword(descentFormHandler.getPassword());
		descentForm.setProfile(profileDAO.save(profileDb));
		return descentFormDAO.save(descentForm);

	}

	@Override
	public DescentForm uploadDescentDoc(String email, MultipartFile file) {
		DescentForm descentForm = descentFormDAO.findByProfileEmail(email);

		if (descentForm != null && descentForm.getId() > 0) {
			try {
				ApplicantDocument applicantDoc = DescentFormMapper.saveDocument("" + descentForm.getId(), file);
//				if (fileStoredDetails !=null && fileStoredDetails.size()>0) {
				String appCode = "" + descentForm.getId();
				String base29Code = PicaStringGenerator.generateBase29Code("BH");
				String custId = "" + descentForm.getId();

				Profile profile = profileDAO.findByEmail(email);
				profile.setStatus("submitted");
				profile.setAppCode(appCode);
				profile.setBase29Code(base29Code);
				profile.setCustId(custId);
				
				
				Application application = new Application(PICAApplictions.DPA.getApplication(),
						FormStatus.SUBMITTED.getStatus(), appCode , custId , base29Code);
				
				//profile.setAppliedFor(PICAMapper.getAppliedForPayload(application,profile.getAppliedFor()));
				
				
				profile.setAppliedFor(PICAApplictions.DPA.getApplication());
				profile.setApplied(PICAApplictions.DPA);

				profile = profileDAO.save(profile);

				List<ApplicantDocument> applicantDocs = descentForm.getDocuments();
				if (applicantDocs == null)
					applicantDocs = new ArrayList<>();

				applicantDocs.add(applicantDoc);
				descentForm.setDocuments(applicantDocs);
				descentForm.setProfile(profile);
				descentForm.setAppliedDate(DateTimeFormatter.ofPattern("dd/MM/YYYY").format(LocalDateTime.now()));
				descentForm = descentFormDAO.save(descentForm);
				LOG.info("descent form list after uplading document ", descentForm);

				subject = "Descent Application Form status ";
				message = EmailMessageTemplate.getDescentApplicationMessageTemplate(descentForm);
				if (isEmailEnabled.equalsIgnoreCase("true"))
					notificationConfig.sendMail(new String[] { email }, subject, message, from);
//				}

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return descentForm;
	}

	@Override
	public Profile validateEmailAddress(String email) {
		Profile profile = profileDAO.findByEmail(email);
		if (profile != null) {
			email = email.toLowerCase();
			String url = "http://localhost:4200/resetPassword/" + email + "/";
			subject = "Password reset on jamaican application";
			message = EmailMessageTemplate.getForgotPasswordMessageTemplate(url);
			try {
				if (isEmailEnabled.equalsIgnoreCase("true"))
					notificationConfig.sendMail(new String[] { email }, subject, message, from);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return profile;
	}

	@Override
	public Profile resetPassword(Map<String, String> payload) {
		Profile profile = profileDAO.findByEmail(payload.get("email"));
		if (profile != null) {
			profile.setPassword(payload.get("password"));
			profile.setPasswordChanged(true);
			profile = profileDAO.save(profile);
		}
		return profile;
	}

	@Override
	public Profile checkEmailAddress(String email) {
		return profileDAO.findByEmail(email);
	}

	@Override
	public Profile checkApplicationStatus(String appCode) {
		DescentForm descentForm = descentFormDAO.findByAppCode(appCode);
		if (descentForm != null) {
			Profile profile = profileDAO.findByEmail(descentForm.getProfile().getEmail());
			if (profile != null)
				profile.setAppliedDate(descentForm.getAppliedDate());
			return profile;
		}
		return null;
	}

	@Override
	public DescentForm getDescentForm(String email) {
		return descentFormDAO.findByProfileEmail(email);
	}

	@Override
	public DescentForm getDescentFormData(Map<String, String> payload) {
		return descentFormDAO.findByAppCodeAndDOB(payload.get("appCode"), payload.get("appDate"));
	}

	@Override
	public List<?> getReviewForms(String formType, String type) {
		List<Profile> profileList = null;
		
		List<String> status = new ArrayList<>();
		
		if("supervisor".equalsIgnoreCase(type)) {
			status.add(FormStatus.SUBMITTED.getStatus());
			status.add(FormStatus.REFFERED.getStatus());
		}
		
		if("localdeskclerk".equalsIgnoreCase(type)) {
			status.add(FormStatus.SCHEDULE.getStatus());
		}
		
		if("compliancesupervisor".equalsIgnoreCase(type) || "cs".equalsIgnoreCase(type)) {
			status.add(FormStatus.CS.getStatus());
		}
		
		if("operationsmanager".equalsIgnoreCase(type) || "om".equalsIgnoreCase(type)) {
			status.add(FormStatus.OM.getStatus());
		}

		if("director".equalsIgnoreCase(type)) {
			status.add(FormStatus.DIRECTOR.getStatus());
		}
		
		if("ceo".equalsIgnoreCase(type)) {
			status.add(FormStatus.CEO.getStatus());
		}
		
		if("permanentsecretary".equalsIgnoreCase(type) || "ps".equalsIgnoreCase(type)) {
			status.add(FormStatus.PERMANENTSECRETARY.getStatus());
		}
		
		if("review".equalsIgnoreCase(type) || "ps".equalsIgnoreCase(type)) {
			status.add(FormStatus.REVIEW.getStatus());
		}
		
		
		
		
		
		if (formType.equalsIgnoreCase(PICAApplictions.DPA.toString())) {
			
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.DPA.toString(),
					status);
			if (profileList != null && profileList.size() > 0) {

				List<Integer> list = new ArrayList<>();
				for (Profile profile : profileList) {
					list.add(Integer.parseInt(profile.getAppCode()));
				}

				return descentFormDAO.findBy_id(list);

			}
			
		}

		else if (formType.equalsIgnoreCase(PICAApplictions.CJA.toString())) {
			return null;
			
			/*profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.CJA.toString(),
					status);
			if (profileList != null && profileList.size() > 0) {

				List<Integer> list = new ArrayList<>();
				for (Profile profile : profileList) {
					list.add(Integer.parseInt(profile.getAppCode()));
				}

				return descentFormDAO.findBy_id(list);

			} */
		}

		else if (formType.equalsIgnoreCase(PICAApplictions.JCRM.toString())) {
			
		/*	profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.JCRM.toString(),
					status);
			if (profileList != null && profileList.size() > 0) {

				List<Integer> list = new ArrayList<>();
				for (Profile profile : profileList) {
					list.add(Integer.parseInt(profile.getAppCode()));
				}

				return descentFormDAO.findBy_id(list); 
			

			}*/
			return null;
		}

		else if (formType.equalsIgnoreCase(PICAApplictions.NACW.toString())) {
			
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.NACW.toString(),
					status);
			if (profileList != null && profileList.size() > 0) {

				List<Integer> list = new ArrayList<>();
				for (Profile profile : profileList) {
					list.add(Integer.parseInt(profile.getAppCode()));
				}
				List<NaturalizationAliens> naturalizationAliens =  naturalizationAliensDAO.findBy_id(list);
				return naturalizationAliens;
			}
		}

		else if (formType.equalsIgnoreCase(PICAApplictions.RGJC.toString())) {
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.RGJC.toString(),
					status);
			
			if (profileList != null && profileList.size() > 0) {

				List<Integer> list = new ArrayList<>();
				for (Profile profile : profileList) {
					list.add(Integer.parseInt(profile.getAppCode()));
				}

				return descentFormDAO.findBy_id(list);

			}
		}

		else if (formType.equalsIgnoreCase(PICAApplictions.RC.toString())) {
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.RC.toString(),
					status);			
			if (profileList != null && profileList.size() > 0) {

				List<Integer> list = new ArrayList<>();
				for (Profile profile : profileList) {
					list.add(Integer.parseInt(profile.getAppCode()));
				}

				return descentFormDAO.findBy_id(list);

			}
		}

		else if (formType.equalsIgnoreCase(PICAApplictions.RCWC.toString())) {
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.RCWC.toString(),
					status);			
			if (profileList != null && profileList.size() > 0) {

				List<Integer> list = new ArrayList<>();
				for (Profile profile : profileList) {
					list.add(Integer.parseInt(profile.getAppCode()));
				}

				return descentFormDAO.findBy_id(list);

			}
		}

		else if (formType.equalsIgnoreCase(PICAApplictions.RVM.toString())) {
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.RVM.toString(),
					status);
			if (profileList != null && profileList.size() > 0) {

				List<Integer> list = new ArrayList<>();
				for (Profile profile : profileList) {
					list.add(Integer.parseInt(profile.getAppCode()));
				}

				return descentFormDAO.findBy_id(list);

			}
		}

		else if (formType.equalsIgnoreCase(PICAApplictions.RJC.toString())) {
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.RJC.toString(),
					status);
			if (profileList != null && profileList.size() > 0) {

				List<Integer> list = new ArrayList<>();
				for (Profile profile : profileList) {
					list.add(Integer.parseInt(profile.getAppCode()));
				}

				return descentFormDAO.findBy_id(list);

			}
		}

		else {
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.DPA.toString(),
					status);
			if (profileList != null && profileList.size() > 0) {

				List<Integer> list = new ArrayList<>();
				for (Profile profile : profileList) {
					list.add(Integer.parseInt(profile.getAppCode()));
				}

				return descentFormDAO.findBy_id(list);

			}
		}

		

		return null;
	}

	@Override
	public List<Agent> getAgents(String formType,String type) {

		if (formType == null || type == null)
			return null;

		List<Agent> agentList = agentDAO.findAll();
		List<Agent> filteredAgentList = new ArrayList<Agent>();
		
		if(agentList != null)
		
		agentList.forEach(agent -> {
			ArrayList<Applicant> applicants = agent.getApplications();
			ArrayList<Applicant> filteredApplicants = new ArrayList<>();

			
			if (applicants != null)
				applicants.forEach(applicant -> {
					if (applicant.getAppliedFor() != null)
						if (formType.equalsIgnoreCase(applicant.getAppliedFor()) ) {
							if(profileDAO.findByAppCodeAndStatus(""+applicant.getApplicantId(),type)!=null)
								filteredApplicants.add(applicant);
						}
				});
			Agent filteredAgent = new Agent();
			filteredAgent.set_id(agent.get_id());
			filteredAgent.setApplications(filteredApplicants);
			filteredAgent.setFormType(agent.getFormType());
			filteredAgent.setName(agent.getName());

			filteredAgentList.add(filteredAgent);
		});

		return filteredAgentList;

	}
	
	@Override
	public List<DeskClerk> getDeskClerk(String formType) {
	//	return deskClerkDAO.findAll();
		
		if (formType == null)
			return null;

		List<DeskClerk> agentList = deskClerkDAO.findAll();
		List<DeskClerk> filteredAgentList = new ArrayList<DeskClerk>();
		
		if(agentList != null)
		
		agentList.forEach(deskClerk -> {
			ArrayList<Applicant> applicants = deskClerk.getApplications();
			ArrayList<Applicant> filteredApplicants = new ArrayList<>();

			if (applicants != null)
				applicants.forEach(applicant -> {
					if (applicant.getAppliedFor() != null)
						if (formType.equalsIgnoreCase(applicant.getAppliedFor())) {
							filteredApplicants.add(applicant);
						}
				});
			DeskClerk filteredAgent = new DeskClerk();
			filteredAgent.set_id(deskClerk.get_id());
			filteredAgent.setApplications(filteredApplicants);
			filteredAgent.setFormType(deskClerk.getFormType());
			filteredAgent.setName(deskClerk.getName());

			filteredAgentList.add(filteredAgent);
		});

		return filteredAgentList;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public Supervisor assignApplicationToAgent(AssignedApplicationPayload payload) {
		String application = payload.getAppType();
		
		if(application == null)
			return null;
		
		List<Applicant> appCodeList = payload.getAppCodes();

		List<Integer> applicantCodes = AdminReviewFormMapper.getApplicantCodes(payload.getAppCodes());
		
			if(applicantCodes!=null && applicantCodes.size()>0) {
			
			if(application.equalsIgnoreCase(PICAApplictions.DPA.toString())){
				List<DescentForm> applicationList = descentFormDAO.findBy_id(applicantCodes);

				if (applicationList.size() > 0) {
					Agent agent = agentDAO.findBy_Id(payload.getAgent());
					agent = agentDAO.save(AdminReviewFormMapper.syncDBAgent(agent, appCodeList));

					applicationList.forEach((descent) -> {

						Profile profile = descent.getProfile();
						if (profile != null && profile.getEmail() != null) {
							profile = profileDAO.findBy_Id(profile.getId());
							profile.setStatus(FormStatus.REVIEW.getStatus());
							profile = profileDAO.save(profile);
							descent.setProfile(profile);
						}
						descent.setStatus(FormStatus.REVIEW.getStatus());
						descentFormDAO.save(descent);
					});
					System.out.println();

					Supervisor supervisor = new Supervisor();
//					Supervisor superVisor = supervisorDAO.findByAgent_id(payload.getAgent());
//					if (superVisor == null)
//						superVisor = new Supervisor();

//					superVisor.setAgent(agent);
					//return supervisorDAO.save(superVisor);
					return supervisor;
			}
		}
			if(application.equalsIgnoreCase(PICAApplictions.NACW.toString())){
				List<NaturalizationAliens> applicationList = naturalizationAliensDAO.findBy_id(applicantCodes);

				if (applicationList.size() > 0) {
					Agent agent = agentDAO.findBy_Id(payload.getAgent());
					agent = agentDAO.save(AdminReviewFormMapper.syncDBAgent(agent, appCodeList));

					applicationList.forEach((naturalisation) -> {

						Profile profile = naturalisation.getProfile();
						if (profile != null && profile.getEmail() != null) {
							profile = profileDAO.findBy_Id(profile.getId());
							profile.setStatus(FormStatus.REVIEW.getStatus());
							profile = profileDAO.save(profile);
							naturalisation.setProfile(profile);
						}
						naturalisation.setStatus(FormStatus.REVIEW.getStatus());
						naturalizationAliensDAO.save(naturalisation);
					});
					System.out.println();

					Supervisor supervisor = new Supervisor();
//					Supervisor superVisor = supervisorDAO.findByAgent_id(payload.getAgent());
//					if (superVisor == null)
//						superVisor = new Supervisor();

//					superVisor.setAgent(agent);
					//return supervisorDAO.save(superVisor);
					return supervisor;
			}
		}
		

		}
		return null;
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public Supervisor assignApplicationToDeskClerk(AssignedApplicationPayload payload) {
		
		String application = payload.getAppType();
		
		if(application == null)
			return null;
		
		List<Applicant> appCodeList = payload.getAppCodes();

		
		List<Integer> applicantCodes = AdminReviewFormMapper.getApplicantCodes(payload.getAppCodes());
		
		if(applicantCodes!=null && applicantCodes.size()>0) {
			
			if(application.equalsIgnoreCase(PICAApplictions.DPA.toString())){
				
				List<DescentForm> applicationList = descentFormDAO.findBy_id(applicantCodes);
				
				DeskClerk deskClerk = deskClerkDAO.findBy_Id(payload.getAgent());
				deskClerk = deskClerkDAO.save(AdminReviewFormMapper.syncDBDeskClerk(deskClerk, appCodeList));

				applicationList.forEach((descent) -> {

					Profile profile = descent.getProfile();
					if (profile != null && profile.getEmail() != null) {
						profile = profileDAO.findBy_Id(profile.getId());
						profile.setStatus(FormStatus.PROCESSING.getStatus());
						profile = profileDAO.save(profile);
						descent.setProfile(profile);
					}
					descent.setStatus(FormStatus.PROCESSING.getStatus());
					descentFormDAO.save(descent);
				});
				System.out.println();

				Supervisor superVisor = new Supervisor();
			
				return superVisor;
			}
			
			if(application.equalsIgnoreCase(PICAApplictions.NACW.toString())){

				
				List<NaturalizationAliens> applicationList = naturalizationAliensDAO.findBy_id(applicantCodes);
				
				DeskClerk deskClerk = deskClerkDAO.findBy_Id(payload.getAgent());
				deskClerk = deskClerkDAO.save(AdminReviewFormMapper.syncDBDeskClerk(deskClerk, appCodeList));

				applicationList.forEach((naturalization) -> {

					Profile profile = naturalization.getProfile();
					if (profile != null && profile.getEmail() != null) {
						profile = profileDAO.findBy_Id(profile.getId());
						profile.setStatus(FormStatus.PROCESSING.getStatus());
						profile = profileDAO.save(profile);
						naturalization.setProfile(profile);
					}
					naturalization.setStatus(FormStatus.PROCESSING.getStatus());
					naturalizationAliensDAO.save(naturalization);
				});
				System.out.println();

				Supervisor superVisor = new Supervisor();
			
				return superVisor;
			
			}
			
		/*	if(application.equalsIgnoreCase(PICAApplictions.DPA.toString()){
				
			}

			if(application.equalsIgnoreCase(PICAApplictions.DPA.toString()){
	
			}
			
			if(application.equalsIgnoreCase(PICAApplictions.DPA.toString()){
				
			}
			
			if(application.equalsIgnoreCase(PICAApplictions.DPA.toString()){
				
			} */
		}
		
		
		
		List<DescentForm> applicationList = descentFormDAO.findBy_id(applicantCodes);

		if (applicationList.size() > 0) {
			DeskClerk deskClerk = deskClerkDAO.findBy_Id(payload.getAgent());
			deskClerk = deskClerkDAO.save(AdminReviewFormMapper.syncDBDeskClerk(deskClerk, appCodeList));

			applicationList.forEach((descent) -> {

				Profile profile = descent.getProfile();
				if (profile != null && profile.getEmail() != null) {
					profile = profileDAO.findBy_Id(profile.getId());
					profile.setStatus(FormStatus.PROCESSING.getStatus());
					profile = profileDAO.save(profile);
					descent.setProfile(profile);
				}
				descent.setStatus(FormStatus.PROCESSING.getStatus());
				descentFormDAO.save(descent);
			});
			System.out.println();

			Supervisor superVisor = new Supervisor();
		
			return superVisor;

		}
		return null;
	}
	

	@Override
	public Agent getAgentDetails(Profile profile) {
		if (profile.getEmail() != null)
			return agentDAO.findBy_Id(Integer.parseInt(profile.getEmail()));

		return null;
	}

	@Override
	public List<?> getApplicantForm(String applicantId,String formType) {

		if(formType.equalsIgnoreCase(PICAApplictions.DPA.toString())) {
			
			DescentForm descentForm = descentFormDAO.findBy_id(Integer.parseInt(applicantId));
			List<DescentForm> list = new ArrayList<DescentForm>();
			if(descentForm!=null)
				list.add(descentForm);
			return list;
		}
		
		if(formType.equalsIgnoreCase(PICAApplictions.NACW.toString())) {
			
			NaturalizationAliens naturalizationForm = naturalizationAliensDAO.findBy_id(Integer.parseInt(applicantId));
			List<NaturalizationAliens> list = new ArrayList<NaturalizationAliens>();
			if(naturalizationForm!=null)
				list.add(naturalizationForm);
			return list;
		}
		
		return null;
		
	}

	
	@Override
	public DeskClerk updateApplicantStatusInDeskClerk(Map<String, String> payload) {

		String applicantId = payload.get("applicantId");
		String status = payload.get("status");
		String agentId = payload.get("agentId");
		String formType = payload.get("formType");
		Profile profile = null;
		DescentForm descentForm = null;
		NaturalizationAliens naturalAliens = null;
		String message = null;

		if (applicantId != null && status != null && agentId !=null && formType !=null) {
			int applicant = Integer.parseInt(applicantId);
			profile = profileDAO.findByCustId(applicantId);

			if (profile != null) {
				profile.setStatus(FormStatus.getByValue(status).getStatus());
				profile.setComment(payload.get("comments"));
				profile = profileDAO.save(profile);

				if(formType.equalsIgnoreCase(PICAApplictions.DPA.toString())) {
					descentForm = descentFormDAO.findBy_id((applicant));
					descentForm.setProfile(profile);
					descentForm.setStatus(FormStatus.getByValue(status).getStatus());
					descentForm = descentFormDAO.save(descentForm);
				}
				
				if(formType.equalsIgnoreCase(PICAApplictions.NACW.toString())) {
					naturalAliens = naturalizationAliensDAO.findBy_id(applicant);
					naturalAliens.setProfile(profile);
					naturalAliens.setStatus(FormStatus.getByValue(status).getStatus());
				}
				
				DeskClerk deskClerk = deskClerkDAO.findBy_Id(Integer.parseInt(agentId));
				List<Applicant> dbList = deskClerk.getApplications();
				ArrayList<Applicant> list = new ArrayList<>();
				for(Applicant data:dbList) {
					if(data.getApplicantId()!= applicant) {
						list.add(data);
					}
				}
				
				message = EmailMessageTemplate.getApplicantStatusUpdateMail(profile);
				try {
					if (isEmailEnabled.equalsIgnoreCase("true"))
						notificationConfig.sendMail(new String[] { profile.getEmail() }, subject, message, from);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				
				
				deskClerk.setApplications(list);
				return deskClerkDAO.save(deskClerk);
			}
			
		}
		return null;
	}
	
	@Override
	public Agent updateApplicantStatus(Map<String, String> payload) {

		String applicantId = payload.get("applicantId");
		String status = payload.get("status");
		String agentId = payload.get("agentId");
		String formType = payload.get("formType");
		Profile profile = null;
		DescentForm descentForm = null;
		NaturalizationAliens natrualizationAliens = null;
		String message = null;

		if (applicantId != null && status != null && agentId !=null) {
			int applicant = Integer.parseInt(applicantId);
			profile = profileDAO.findByCustId(applicantId);

			if (profile != null) {
				profile.setStatus(FormStatus.getByValue(status).getStatus());
				profile.setComment(payload.get("comments"));
				profile = profileDAO.save(profile);

				if(formType==null)
					formType = profile.getAppliedFor();
				
				if(formType.equalsIgnoreCase(PICAApplictions.DPA.toString())) {
					descentForm = descentFormDAO.findBy_id((applicant));
					descentForm.setProfile(profile);
					descentForm.setStatus(FormStatus.getByValue(status).getStatus());
					descentForm = descentFormDAO.save(descentForm);
				}
				
				if(formType.equalsIgnoreCase(PICAApplictions.NACW.toString())) {
					natrualizationAliens = naturalizationAliensDAO.findBy_id(Integer.parseInt(applicantId));
					natrualizationAliens.setProfile(profile);
					natrualizationAliens.setStatus(FormStatus.getByValue(status).getStatus());
					natrualizationAliens = naturalizationAliensDAO.save(natrualizationAliens);
				}
System.out.println();				
				if(status.equalsIgnoreCase("om")) {
					status = FormStatus.REVIEW.getStatus();
				}
			
				Agent agent = agentDAO.findBy_Id(Integer.parseInt(agentId));
				List<Applicant> dbList = agent.getApplications();
				ArrayList<Applicant> list = new ArrayList<>();
				for(Applicant data:dbList) {
					if( profileDAO.findByAppCodeAndStatusAndApplied(""+data.getApplicantId(), status, formType.toUpperCase())!=null) {
						list.add(data);
					}
				}
				
				message = EmailMessageTemplate.getApplicantStatusUpdateMail(profile);
				try {
					if (isEmailEnabled.equalsIgnoreCase("true"))
						notificationConfig.sendMail(new String[] { profile.getEmail() }, subject, message, from);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				
				
				agent.setApplications(list);
				return agent;
			}
			
		}
		return null;
	}

	@Override
	public Profile scheduleAppointment(Map<String, String> payload) {
		String applicantId = payload.get("applicantId");
		String applied = payload.get("applied");

		Profile profile = profileDAO.findByAppCode(applicantId);
		if (profile != null && applied != null) {
			profile.setStatus(FormStatus.SCHEDULE.getStatus());
			profile.setTime(payload.get("time"));
			profile.setDate(payload.get("date"));
//			profile.setTime(Time.valueOf(payload.get("time")));
//			profile.setDate(formatDate(payload.get("date")));

			profile = profileDAO.save(profile);
			if(applied.equalsIgnoreCase(PICAApplictions.DPA.toString())) {
				DescentForm descentForm = descentFormDAO.findByAppCode(applicantId);
				descentForm.setProfile(profile);
				descentFormDAO.save(descentForm);
			}
			
			if(applied.equalsIgnoreCase(PICAApplictions.NACW.toString())) {
				NaturalizationAliens naturalizationAliens = naturalizationAliensDAO.findByAppCode(applicantId);
				naturalizationAliens.setProfile(profile);
				naturalizationAliensDAO.save(naturalizationAliens);
			}
			
			
			try {
			message = EmailMessageTemplate.getApplicantStatusUpdateMail(profile);

			if (isEmailEnabled.equalsIgnoreCase("true"))
				notificationConfig.sendMail(new String[] { profile.getEmail() }, subject, message, "dummy");
			}catch(Exception e) {
				e.printStackTrace();
			}
			LOG.info("profile data after updating a status of schedule appointment ",profile);
			return profile;
		}
		LOG.info("appointment didn't schedule due to mismatch of payload");
		return null;
	}

	private Date formatDate(String string) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMMMM dd yyyy");
		try {
			return sdf.parse(string);
		} catch (ParseException e) {
			//TODO need to repalce with proper exception handling
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Profile validateAppointment(String applicantId) {
		
		Profile profile = profileDAO.findByAppCode(applicantId);
		
		
		if(profile !=null && profile.getStatus().equals(FormStatus.PAS.getStatus())){
			return profile;
		}
		return null;
	}

	@Override
	public Roles validateOffical(Roles roles) {
		roles =  rolesDAO.findByUserIdAndPassword(roles.getUserId(), roles.getPassword());
		return roles;
	}

	@Override
	public Agent getAgentApplicants(String agentId, String formType) {
		
		Roles roles = rolesDAO.findByUserId(agentId);
		if(roles ==null) {
			LOG.info("agentId is invalid ",agentId);
			return null;
		}
		//To-do need to filter on top of formType
//		Agent agent = agentDAO.findBy_idAndFormType(Integer.parseInt(agentId),formType);
		Agent agent = agentDAO.findBy_Id(Integer.parseInt(agentId));
		
		if(agent == null) {
			LOG.info("desckClerk with clerkId "+agentId+", doesn't hold any applicant "+agent);
			return null;
		}
		
		ArrayList<Applicant> applicationList = agent.getApplications();
		ArrayList<Applicant> typeOfApplicant = new ArrayList<Applicant>();
		applicationList.forEach(applicant->{
			
			if(formType.equalsIgnoreCase(applicant.getAppliedFor()) && profileDAO.findByAppCodeAndStatus(""+applicant.getApplicantId(), FormStatus.REVIEW.getStatus())!=null) {
				typeOfApplicant.add(applicant);
			}
			
		});
		agent.setFormType(formType);
		agent.setApplications(typeOfApplicant);
		
		LOG.info("agentId "+agentId+" has following list of applicant "+agent.toString());
		return agent;
	}

	@Override
	public DeskClerk getDeskClerkApplicants(String clerkId, String formType) {
		Roles roles = rolesDAO.findByUserId(clerkId);
		if(roles ==null) {
			LOG.info("agentId is invalid ",clerkId);
			return null;
		}
		//To-do need to filter on top of formType
		//DeskClerk deskClerk = deskClerkDAO.findBy_idAndFormType(Integer.parseInt(clerkId),formType);
		DeskClerk deskClerk = deskClerkDAO.findBy_Id(Integer.parseInt(clerkId));
		if(deskClerk == null) {
			LOG.info("desckClerk with clerkId "+clerkId+", doesn't hold any applicant "+deskClerk);
			return null;
		}
		ArrayList<Applicant> applicationList = deskClerk.getApplications();
		ArrayList<Applicant> typeOfApplicant = new ArrayList<Applicant>();
		applicationList.forEach(applicant->{
			
			if(formType.equalsIgnoreCase(applicant.getAppliedFor())&& profileDAO.findByAppCodeAndStatus(applicant.getApplicantId(), FormStatus.PROCESSING.getStatus())!=null) {
				typeOfApplicant.add(applicant);
			}
			
		});
		deskClerk.setFormType(formType);
		deskClerk.setApplications(typeOfApplicant);
		
		LOG.info("agentId "+clerkId+" has following list of applicant "+deskClerk.toString());
		return deskClerk;
	}

	@Override
	public List<?> updateApplicantStatusInProfile(Map<String, String> payload) {
		String applicantId = payload.get("applicantId");
		String status = payload.get("status");
		String formType = payload.get("formType");

		Profile profile = null;
		DescentForm descentForm = null;
		NaturalizationAliens naturalizationAliens = null;
		String message = null;

		if (applicantId != null && status != null && formType !=null) {
			int applicant = Integer.parseInt(applicantId);
			profile = profileDAO.findByCustId(applicantId);

			if (profile != null) {
				profile.setStatus(FormStatus.getByValue(status).getStatus());
				profile.setComment(payload.get("comments"));
				profile = profileDAO.save(profile);

				
				
				if(formType.equalsIgnoreCase(PICAApplictions.DPA.toString())) {
					descentForm = descentFormDAO.findBy_id((applicant));
					descentForm.setProfile(profile);
					descentForm.setStatus(FormStatus.getByValue(status).getStatus());
					descentForm = descentFormDAO.save(descentForm);
					
					if(status.equalsIgnoreCase("cs")) {
						status = "localdeskclerk";
					}
					if(status.equalsIgnoreCase("om")) {
						status="review";
					}
					if(status.equalsIgnoreCase("director")) {
						status = "om";
					}
					if(status.equalsIgnoreCase("completed")) {
						status = "director";
					}
				
					
					return this.getReviewForms(formType, status);
				}
				
				if(formType.equalsIgnoreCase(PICAApplictions.NACW.toString())) {
					naturalizationAliens = naturalizationAliensDAO.findBy_id(applicant);
					naturalizationAliens.setProfile(profile);
					naturalizationAliens.setStatus(FormStatus.getByValue(status).getStatus());
					naturalizationAliens = naturalizationAliensDAO.save(naturalizationAliens);
					if(status.equalsIgnoreCase("cs")) {
						status = "localdeskclerk";
					}
					if(status.equalsIgnoreCase("om")) {
						status="review";
					}
					if(status.equalsIgnoreCase("director")) {
						status="om";
					}
					if(status.equalsIgnoreCase("ceo")) {
						status="director";
					}
					if(status.equalsIgnoreCase("permanentsecretary")) {
						status="ceo";
					}
					if(status.equalsIgnoreCase("completed")) {
						status = "permanentsecretary";
					}
					
//					if(status.equalsIgnoreCase("cs")) {
//						status = FormStatus.SCHEDULE.getStatus();
//					}
					return this.getReviewForms(formType, status);
				}
				
				
			}
		}
		return null;

	}

}

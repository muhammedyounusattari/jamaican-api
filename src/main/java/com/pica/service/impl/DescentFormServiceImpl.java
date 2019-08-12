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
import com.pica.dao.RolesDAO;
import com.pica.dao.SequenceDAO;
import com.pica.dao.SupervisorDAO;
import com.pica.mapper.AdminReviewFormMapper;
import com.pica.mapper.DescentFormHandler;
import com.pica.mapper.DescentFormMapper;
import com.pica.mapper.EmailMessageTemplate;
import com.pica.model.Agent;
import com.pica.model.AllForms;
import com.pica.model.Applicant;
import com.pica.model.ApplicantDocument;
import com.pica.model.DescentForm;
import com.pica.model.DeskClerk;
import com.pica.model.Profile;
import com.pica.model.Roles;
import com.pica.model.Supervisor;
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

	@Value("${email.enabled}")
	private String isEmailEnabled;

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
	public List<DescentForm> getReviewForms(String formType) {
		List<Profile> profileList = null;
		
		List<String> status = new ArrayList<>();
		status.add(FormStatus.SUBMITTED.getStatus());
		status.add(FormStatus.REFFERED.getStatus());

		if (formType.equalsIgnoreCase(PICAApplictions.DPA.toString()))
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.DPA.toString(),
					status);

		else if (formType.equalsIgnoreCase(PICAApplictions.CJA.getApplication()))
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.CJA.getApplication(),
					status);

		else if (formType.equalsIgnoreCase(PICAApplictions.JCRM.getApplication()))
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.JCRM.getApplication(),
					status);

		else if (formType.equalsIgnoreCase(PICAApplictions.NACW.getApplication()))
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.NACW.getApplication(),
					status);

		else if (formType.equalsIgnoreCase(PICAApplictions.RGJC.getApplication()))
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.RGJC.getApplication(),
					status);

		else if (formType.equalsIgnoreCase(PICAApplictions.RC.getApplication()))
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.RC.getApplication(),
					status);

		else if (formType.equalsIgnoreCase(PICAApplictions.RCWC.getApplication()))
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.RCWC.getApplication(),
					status);

		else if (formType.equalsIgnoreCase(PICAApplictions.RVM.getApplication()))
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.RVM.getApplication(),
					status);

		else if (formType.equalsIgnoreCase(PICAApplictions.RJC.getApplication()))
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.RJC.getApplication(),
					status);

		else
			profileList = profileDAO.findByAppliedAndStatus(PICAApplictions.DPA.getApplication(),
					status);

		if (profileList != null && profileList.size() > 0) {

			List<Integer> list = new ArrayList<>();
			for (Profile profile : profileList) {
				list.add(Integer.parseInt(profile.getAppCode()));
			}

			return descentFormDAO.findBy_id(list);

		}

		return null;
	}

	@Override
	public List<Agent> getAgents() {

		return agentDAO.findAll();
	}
	
	@Override
	public List<DeskClerk> getDeskClerk() {
		return deskClerkDAO.findAll();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Supervisor assignApplicationToAgent(AssignedApplicationPayload payload) {
		List<Applicant> appCodeList = payload.getAppCodes();

		List<Integer> applicantCodes = AdminReviewFormMapper.getApplicantCodes(payload.getAppCodes());
		List<DescentForm> applicationList = descentFormDAO.findBy_id(applicantCodes);

		if (applicationList.size() > 0) {
			Agent agent = agentDAO.findBy_Id(payload.getAgent());
			agent = agentDAO.save(AdminReviewFormMapper.syncDBAgent(agent, appCodeList));

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

			Supervisor superVisor = supervisorDAO.findByAgent_id(payload.getAgent());
			if (superVisor == null)
				superVisor = new Supervisor();

			superVisor.setAgent(agent);
			return supervisorDAO.save(superVisor);

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
	public DescentForm getApplicantForm(String applicantId) {

		return descentFormDAO.findBy_id(Integer.parseInt(applicantId));
	}

	@Override
	public Agent updateApplicantStatus(Map<String, String> payload) {

		String applicantId = payload.get("applicantId");
		String status = payload.get("status");
		String agentId = payload.get("agentId");
		Profile profile = null;
		DescentForm descentForm = null;
		String message = null;

		if (applicantId != null && status != null && agentId !=null) {
			int applicant = Integer.parseInt(applicantId);
			profile = profileDAO.findByCustId(applicantId);

			if (profile != null) {
				profile.setStatus(FormStatus.getByValue(status).getStatus());
				profile.setComment(payload.get("comments"));
				profile = profileDAO.save(profile);

				descentForm = descentFormDAO.findBy_id((applicant));
				descentForm.setProfile(profile);
				descentForm.setStatus(FormStatus.getByValue(status).getStatus());
				descentForm = descentFormDAO.save(descentForm);
				
			
				Agent agent = agentDAO.findBy_Id(Integer.parseInt(agentId));
				List<Applicant> dbList = agent.getApplications();
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
				
				
				agent.setApplications(list);
				return agentDAO.save(agent);
			}
			
		}
		return null;
	}

	@Override
	public Profile scheduleAppointment(Map<String, String> payload) {
		String applicantId = payload.get("applicantId");

		Profile profile = profileDAO.findByAppCode(applicantId);
		if (profile != null) {
			profile.setStatus(FormStatus.PAS.getStatus());
			profile.setTime(payload.get("time"));
			profile.setDate(payload.get("date"));
//			profile.setTime(Time.valueOf(payload.get("time")));
//			profile.setDate(formatDate(payload.get("date")));

			profile = profileDAO.save(profile);
			DescentForm descentForm = descentFormDAO.findByAppCode(applicantId);
			descentForm.setProfile(profile);

			descentFormDAO.save(descentForm);
		}
		return profile;

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
		if(profile.getStatus().equals(FormStatus.PAS.getStatus())){
			return null;
		}
		return profile;
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
		LOG.info("agentId "+clerkId+" has following list of applicant "+deskClerk.toString());
		return deskClerk;
	}

	

}

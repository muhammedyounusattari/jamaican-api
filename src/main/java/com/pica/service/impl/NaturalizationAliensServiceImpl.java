package com.pica.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pica.commons.PICAApplictions;
import com.pica.config.PicaStringGenerator;
import com.pica.dao.CreateProfileDAO;
import com.pica.dao.NaturalizationAliensDAO;
import com.pica.dao.SequenceDAO;
import com.pica.mapper.EmailMessageTemplate;
import com.pica.mapper.NaturalizationAliensHandler;
import com.pica.mapper.NaturalizationAliensMapper;
import com.pica.model.ApplicantDocument;
import com.pica.model.Forms;
import com.pica.model.Profile;
import com.pica.model.natural.alien.NaturalizationAliens;
import com.pica.service.NaturalizationAliensService;

import pica.notification.config.NotificationConfig;

@Service
public class NaturalizationAliensServiceImpl implements NaturalizationAliensService{
	
private String isEmailEnabled="true";;
	
	@Value("${create.profile.subject}")
	private String subject;

	@Value("${create.profile.message}")
	private String message;

	@Value("${create.profile.message}")
	private String from;

	@Autowired
	private NotificationConfig notificationConfig;
	
	private static final Logger LOG = LoggerFactory.getLogger(NaturalizationAliensServiceImpl.class);
	@Autowired
	private NaturalizationAliensDAO naturalizationDAO;
	
	
	@Autowired
	private CreateProfileDAO profileDAO;
	
	@Autowired
	private SequenceDAO sequenceDAO;
	
	private String HOSTING = "hosting";
	
	@Override
	public NaturalizationAliens submitNaturalizationAliens(NaturalizationAliensHandler naturalizationAliensDTO) {

		NaturalizationAliens aliensForm = NaturalizationAliensMapper.formatPayload(naturalizationAliensDTO);
		NaturalizationAliens aliensFormDb = naturalizationDAO.findByProfileEmail(aliensForm.getProfile().getEmail());
		Profile profileDb = profileDAO.findByEmail(aliensForm.getProfile().getEmail());
		profileDb = NaturalizationAliensMapper.syncProfileForm(aliensForm.getProfile(), profileDb);
		
		
				if (aliensFormDb != null) {
					aliensFormDb.setProfile(profileDb);
					aliensForm = NaturalizationAliensMapper.syncNaturalAlienForm(aliensForm, aliensFormDb);
					
					profileDAO.save(aliensForm.getProfile());
		return naturalizationDAO.save(aliensForm);
	}
				aliensForm.set_id((int) sequenceDAO.getNextSequenceIdProfile(HOSTING));
				ArrayList<Forms> forms = new ArrayList<Forms>();
				Forms forms1 = new Forms();
				forms1.setFormAppCode(Integer.valueOf(aliensForm.get_id()).toString());
				forms1.setFormStatus("saved");
				forms1.setFormName(PICAApplictions.NACW.getApplication());
				forms.add(forms1);
				
				
				if(profileDb.getForms() != null) {
					 profileDb.getForms().addAll(forms);
					 
				}else {
					 profileDb.setForms(forms );
				}
				aliensForm.setProfile(profileDAO.save(profileDb));
				return naturalizationDAO.save(aliensForm);
		
	}


	
	
	
	@Override
	public NaturalizationAliens uploadNaturalAlientDoc(String email, MultipartFile file) {
		

		NaturalizationAliens aliensFormDb = naturalizationDAO.findByProfileEmail(email);

		if (aliensFormDb != null && aliensFormDb.get_id() > 0) {
			try {
				ApplicantDocument applicantDoc = NaturalizationAliensMapper.saveDocument("" + aliensFormDb.get_id(), file);
//				if (fileStoredDetails !=null && fileStoredDetails.size()>0) {
				String appCode = "" + aliensFormDb.get_id();
				String base29Code = PicaStringGenerator.generateBase29Code("BH");
				String custId = "" + aliensFormDb.get_id();

				Profile profile = profileDAO.findByEmail(email);
				 
				for(Forms form: profile.getForms()) {
					if(form.getFormAppCode().equals(Integer.valueOf(aliensFormDb.get_id()).toString())) {
						form.setFormStatus("submitted");
						form.setFormAppCode(appCode);
						form.setBase29Code(base29Code);
						form.setCustId(custId);
						form.setAppliedFor(PICAApplictions.NACW.getApplication());
						form.setApplied(PICAApplictions.NACW);
					}
				}
				

				profile = profileDAO.save(profile);

				List<ApplicantDocument> applicantDocs = aliensFormDb.getDocuments();
				if (applicantDocs == null)
					applicantDocs = new ArrayList<>();

				applicantDocs.add(applicantDoc);
				aliensFormDb.setDocuments(applicantDocs);
				aliensFormDb.setProfile(profile);
				aliensFormDb.setAppliedDate(DateTimeFormatter.ofPattern("dd/MM/YYYY").format(LocalDateTime.now()));
				aliensFormDb = naturalizationDAO.save(aliensFormDb);
				LOG.info("Naturalisation form list after uplading document ", aliensFormDb);

				subject = "Naturalization Application Form status ";
				message = EmailMessageTemplate.getNaturalizationApplicationMessageTemplate(aliensFormDb);
				if (isEmailEnabled.equalsIgnoreCase("true"))
					notificationConfig.sendMail(new String[] { email }, subject, message, from);
//				}

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return aliensFormDb;
	
	
	}

	@Override
	public NaturalizationAliens getNaturalisationForm(String email) {
		return naturalizationDAO.findByProfileEmail(email);
	}
	


}

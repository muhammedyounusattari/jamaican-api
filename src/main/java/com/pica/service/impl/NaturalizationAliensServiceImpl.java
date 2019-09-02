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

import com.pica.commons.FormStatus;
import com.pica.commons.PICAApplictions;
import com.pica.config.PicaStringGenerator;
import com.pica.dao.CreateProfileDAO;
import com.pica.dao.NaturalizationAliensDAO;
import com.pica.dao.SequenceDAO;
import com.pica.dto.NaturalizationAliensDto;
import com.pica.mapper.DescentFormMapper;
import com.pica.mapper.EmailMessageTemplate;
import com.pica.mapper.NaturalizationAliensMapper;
import com.pica.model.ApplicantDocument;
import com.pica.model.Application;
//import com.pica.mapper.NaturalizationAliensMapper;
import com.pica.model.DescentForm;
import com.pica.model.Profile;
import com.pica.model.natural.alien.NaturalizationAliens;
import com.pica.service.NaturalizationAliensService;

import pica.notification.config.NotificationConfig;

@Service
public class NaturalizationAliensServiceImpl implements NaturalizationAliensService {

	private static final Logger LOG = LoggerFactory.getLogger(NaturalizationAliensServiceImpl.class);

	@Autowired
	private NaturalizationAliensDAO naturalizationDAO;

	@Autowired
	private CreateProfileDAO profileDAO;

	@Autowired
	private SequenceDAO sequenceDAO;
	
	@Autowired
	private NotificationConfig notificationConfig;

	
	@Value("${create.profile.subject}")
	private String subject;

	@Value("${create.profile.message}")
	private String message;

	@Value("${create.profile.message}")
	private String from;

	private String HOSTING = "hosting";

	//@Value("${email.enabled}")
	private String isEmailEnabled="true";
	

	@Override
	public NaturalizationAliens submitNaturalizationAliens(NaturalizationAliensDto naturalizationAliensDTO) {
		
		if(naturalizationAliensDTO.getEmail()==null || naturalizationAliensDTO.getEmail()=="") {
			return null;
		}
		
		LOG.info(("inside submitNaturalizationAliens of " + this.getClass().getName()));

		NaturalizationAliens naturalizationAliensDb = naturalizationDAO
				.findByProfileEmail(naturalizationAliensDTO.getEmail());

		/*if (naturalizationAliensDb != null && naturalizationAliensDb.getProfile().getStatus()!=null)
			return null;*/

		NaturalizationAliens naturalizationAliens = NaturalizationAliens.builder(naturalizationAliensDTO);

		Profile profileDb = profileDAO.findByEmail(naturalizationAliens.getProfile().getEmail());
		if (profileDb == null)
			return null;

		
		/*String appCode = "" + naturalizationAliens.get_id();
		String base29Code = PicaStringGenerator.generateBase29Code("BH");
		String custId = "" + naturalizationAliens.get_id();
		profileDb.setStatus("submitted");
		profileDb.setAppCode(appCode);
		profileDb.setBase29Code(base29Code);
		profileDb.setCustId(custId); */
		profileDb = NaturalizationAliensMapper.syncProfileForm(naturalizationAliens.getProfile(), profileDb);

		if(naturalizationAliensDb !=null) {
		//LOG.info("record if naturialization already exist ", naturalizationAliensDb.toString());
		naturalizationAliensDb.setProfile(profileDb);
		naturalizationAliensDb = NaturalizationAliensMapper.syncNaturalAlienForm(naturalizationAliens,naturalizationAliensDb);
		profileDAO.save(profileDb);
		return naturalizationDAO.save(naturalizationAliensDb);
		}
		
		naturalizationAliens.set_id((int) sequenceDAO.getNextSequenceIdProfile(HOSTING));
		// profileDb.setPassword(descentFormHandler.getPassword());
		LOG.info("record if naturialization before saving ", naturalizationAliens.toString());
		naturalizationAliens.setProfile(profileDAO.save(profileDb));
		return naturalizationDAO.save(naturalizationAliens);

	}

	@Override
	public NaturalizationAliens uploadNaturalAlientDoc(String email, MultipartFile file) {

		NaturalizationAliens naturalizationAliens = naturalizationDAO.findByProfileEmail(email);

		if (naturalizationAliens != null && naturalizationAliens.get_id() > 0) {
			try {
				ApplicantDocument applicantDoc = NaturalizationAliensMapper.saveDocument("" + naturalizationAliens.get_id(), file);
//				if (fileStoredDetails !=null && fileStoredDetails.size()>0) {
				String appCode = "" + naturalizationAliens.get_id();
				String base29Code = PicaStringGenerator.generateBase29Code("BH");
				String custId = "" + naturalizationAliens.get_id();

				Profile profile = profileDAO.findByEmail(email);
				profile.setStatus("submitted");
				profile.setAppCode(appCode);
				profile.setBase29Code(base29Code);
				profile.setCustId(custId);
//				
//				
////				Application application = new Application(PICAApplictions.DPA.getApplication(),
//						FormStatus.SUBMITTED.getStatus(), appCode , custId , base29Code);
//				
//				profile.setAppliedFor(PICAMapper.getAppliedForPayload(application,profile.getAppliedFor()));
				
				
				profile.setAppliedFor(PICAApplictions.NACW.getApplication());
				profile.setApplied(PICAApplictions.NACW);

				profile = profileDAO.save(profile);

				List<ApplicantDocument> applicantDocs = naturalizationAliens.getDocuments();
				if (applicantDocs == null)
					applicantDocs = new ArrayList<>();

				applicantDocs.add(applicantDoc);
				naturalizationAliens.setDocuments(applicantDocs);
				naturalizationAliens.setProfile(profile);
				naturalizationAliens.setAppliedDate(DateTimeFormatter.ofPattern("dd/MM/YYYY").format(LocalDateTime.now()));
				naturalizationAliens = naturalizationDAO.save(naturalizationAliens);
				LOG.info("descent form list after uplading document ", naturalizationAliens.toString());

				subject = "Descent Application Form status ";
				message = EmailMessageTemplate.getDescentApplicationMessageTemplate(naturalizationAliens);
				if (!isEmailEnabled.equalsIgnoreCase("true"))
					notificationConfig.sendMail(new String[] { email }, subject, message, from);
//				}

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return naturalizationAliens;
	
	}

}

package com.pica.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.MongoWriteException;
import com.pica.config.PicaStringGenerator;
import com.pica.dao.AllFormsDAO;
import com.pica.dao.CreateProfileDAO;
import com.pica.dao.DescentFormDAO;
import com.pica.dao.SequenceDAO;
import com.pica.mapper.DescentFormHandler;
import com.pica.mapper.DescentFormMapper;
import com.pica.mapper.EmailMessageTemplate;
import com.pica.model.AllForms;
import com.pica.model.DescentForm;
import com.pica.model.Profile;
import com.pica.service.DescentFormService;

import pica.notification.config.NotificationConfig;

@Service
public class DescentFormServiceImpl implements DescentFormService {

	@Autowired
	private CreateProfileDAO createProfileDAO;

	@Autowired
	private DescentFormDAO descentFormDAO;

	@Autowired
	private AllFormsDAO allFormsDAO;

	@Autowired
	private SequenceDAO sequenceDAO;

	@Autowired
	private NotificationConfig notificationConfig;

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
			profile = createProfileDAO.save(profile);
			message = EmailMessageTemplate.getProfileMessageTemplate(profile);
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
		Profile profile = createProfileDAO.findByEmailAndPassword(createProfile.getEmail(),
				createProfile.getPassword());
		return profile;
	}

	@Override
	public DescentForm submitDescentApplication(DescentFormHandler descentFormHandler) {
		DescentForm descentForm = DescentFormMapper.formatPayload(descentFormHandler);
		DescentForm descentFormDb = descentFormDAO.findByProfileEmail(descentForm.getProfile().getEmail());

		if (descentFormDb != null) {
			descentForm = DescentFormMapper.syncDescentForm(descentForm, descentFormDb);
			createProfileDAO.save(descentForm.getProfile());
			return descentFormDAO.save(descentForm);
		}

		descentForm.setId((int) sequenceDAO.getNextSequenceIdProfile(HOSTING));
		Profile profileDb = createProfileDAO.findByEmail(descentForm.getProfile().getEmail());
		profileDb = DescentFormMapper.syncProfileForm(descentForm.getProfile(), profileDb);
		descentForm.setProfile(createProfileDAO.save(profileDb));
		return descentFormDAO.save(descentForm);

	}

	@Override
	public DescentForm uploadDescentDoc(String email, MultipartFile file) {
		DescentForm descentForm = descentFormDAO.findByProfileEmail(email);

		if (descentForm != null && descentForm.getId() > 0) {
			try {
				if (DescentFormMapper.saveDocument("" + descentForm.getId(), file)) {
					String appCode = "" + descentForm.getId();
					String base29Code = PicaStringGenerator.generateBase29Code("BH");
					String custId = "" + descentForm.getId();

					Profile profile = createProfileDAO.findByEmail(email);
					profile.setStatus("submitted");
					profile.setAppCode(appCode);
					profile.setBase29Code(base29Code);
					profile.setCustId(custId);
					profile.setAppliedFor("Descent Application Form");

					profile = createProfileDAO.save(profile);
					descentForm.setProfile(profile);
					descentForm.setAppliedDate(DateTimeFormatter.ofPattern("dd/MM/YYYY").format(LocalDateTime.now()));
					descentFormDAO.save(descentForm);

					subject = "Descent Application Form status ";
					message = EmailMessageTemplate.getDescentApplicationMessageTemplate(descentForm);
					notificationConfig.sendMail(new String[] { email }, subject, message, from);
				}

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return descentForm;
	}

	@Override
	public Profile validateEmailAddress(String email) {
		Profile profile = createProfileDAO.findByEmail(email);
		if (profile != null) {
			email = email.toLowerCase();
			String url = "http://localhost:4200/resetPassword/" + email + "/";
			subject = "Password reset on jamaican application";
			message = EmailMessageTemplate.getForgotPasswordMessageTemplate(url);
			try {
				notificationConfig.sendMail(new String[] { email }, subject, message, from);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return profile;
	}

	@Override
	public Profile resetPassword(Map<String, String> payload) {
		Profile profile = createProfileDAO.findByEmail(payload.get("email"));
		if (profile != null) {
			profile.setPassword(payload.get("password"));
			return createProfileDAO.save(profile);
		}
		return profile;
	}

	@Override
	public Profile checkEmailAddress(String email) {
		return createProfileDAO.findByEmail(email);
	}

	@Override
	public Profile checkApplicationStatus(String appCode) {
		DescentForm descentForm = descentFormDAO.findByAppCode(appCode);
		if (descentForm != null) {
			Profile profile = createProfileDAO.findByEmail(descentForm.getProfile().getEmail());
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

}

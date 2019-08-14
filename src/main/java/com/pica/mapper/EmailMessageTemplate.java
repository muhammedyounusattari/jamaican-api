package com.pica.mapper;

import java.time.LocalDate;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.pica.commons.FormStatus;
import com.pica.model.DescentForm;
import com.pica.model.Profile;
import com.pica.notification.client.ApplicantManagementEmailTemplate;

public class EmailMessageTemplate {

	@Value("${emai.link.url}")
	private String emailLink;

	@Autowired
	private ApplicantManagementEmailTemplate appMail;

	private static ApplicantManagementEmailTemplate applicantMail;

	@PostConstruct
	public void init() {
		applicantMail = appMail;
	}

	public static String getProfileMessageTemplate(Profile profile) {
		StringBuffer message = new StringBuffer();
		message.append("Thanks for submitting a profile, your username is " + profile.getEmail() + " and password is "
				+ profile.getPassword());
		return new String(message);
	}

	public static String getDescentApplicationMessageTemplate(DescentForm descentForm) {
		StringBuffer message = new StringBuffer();
		Profile profile = descentForm.getProfile();

		message.append(LocalDate.now());
		message.append("<br/><br/>");
		message.append(profile.getFirstname() + "  " + profile.getLastname() + "<br/><br/>");
		message.append(profile.getAddress1() + "," + profile.getAddress2() + "<br/><br/>");
		message.append("Dear Mr./Ms " + profile.getLastname() + "," + "<br/><br/>");
		message.append("Thank you for submitting an application for Descent Application. Your application number is "
				+ profile.getAppCode()
				+ " which may be used to track the progress of your application. Your application and supporting document will be reviewed and an email update provided to you within 5 working days.<br/><br/>");
		message.append("Regards,<br/>");
		message.append("Passport, Immigration and Citizenship<br/>");
		message.append("Agency");
		return new String(message);

	}

	public static String getForgotPasswordMessageTemplate(String url) {
		StringBuffer message = new StringBuffer();
		message.append(
				"You're receiving this e-mail because you requested a password reset for your Jamaican citizen ship account.<br/>");
		message.append("Please go to the following page and choose a new password:<br/>");
		message.append("<a href='" + url + "'>" + url + "</a><br/><br/>");
		message.append("Thanks,<br/>");
		message.append("Passport, Immigration and Citizenship<br/>");
		message.append("Agency");
		return new String(message);
	}

	public static String getApplicantStatusUpdateMail(Profile profile) {
		String status = profile.getStatus();

		if (status.equals(FormStatus.PAS.getStatus())) {
			return applicantMail.onCompleted(profile);
		} else if (status.equals(FormStatus.INCOMPLETE.getStatus())) {
			return applicantMail.onIncomplete(profile);

		} else if (status.equals(FormStatus.DECLINED.getStatus())) {
			return applicantMail.onDeclined(profile);
		} else if (status.equals(FormStatus.PROCESSING.getStatus())) {
			return applicantMail.onProcessing(profile);

		} else if (status.equals(FormStatus.REFFERED.getStatus())) {
			return applicantMail.onReffered(profile);
		} else if (status.equals(FormStatus.SUBMITTED.getStatus())) {
			return applicantMail.onReffered(profile);
		}

		else {
			return null;
		}

	}

}
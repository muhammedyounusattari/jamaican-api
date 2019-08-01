package com.pica.notification.client;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import com.pica.model.Profile;

@PropertySource("classpath:application.properties")
public class ApplicantManagementEmailTemplate {

	@Value("${emai.link.url}")
	private String emailLink;
	private static String link;

	@PostConstruct
	public void init() {
		link = emailLink;
	}

	public static String onProcessing(Profile profile) {
		StringBuffer message = new StringBuffer();
		message.append("Thanks for submitting a profile, your username is " + profile.getEmail() + " and password is "
				+ profile.getPassword());
		return new String(message);
	}

	public static String onIncomplete(Profile profile) {
		StringBuffer message = new StringBuffer();

		message.append(LocalDate.now());
		message.append("<br/><br/>");
		message.append(profile.getFirstname() + "  " + profile.getLastname() + "<br/><br/>");
		message.append(profile.getAddress1() + "," + profile.getAddress2() + "<br/><br/>");
		message.append("Dear Mr./Ms " + profile.getLastname() + "," + "<br/><br/>");

		message.append("Thank you for applying for Citizenship by Descent. Your application <code>"
				+ profile.getCustId()
				+ "</code> has been reviewed, however your application is deemed incomplete. Please review the reason below:");
		message.append("<br/><br/>" + profile.getComment());
		message.append("Regards,<br/>");
		message.append("Passport, Immigration and Citizenship<br/>");
		message.append("Agency");
		return new String(message);
	}

	public static String onDeclined(Profile profile) {
		StringBuffer message = new StringBuffer();
		message.append("Thanks for submitting a profile, your username is " + profile.getEmail() + " and password is "
				+ profile.getPassword());
		return new String(message);
	}

	public static String onReffered(Profile profile) {
		StringBuffer message = new StringBuffer();
		message.append("Thanks for submitting a profile, your username is " + profile.getEmail() + " and password is "
				+ profile.getPassword());
		return new String(message);
	}

	public static String onCompleted(Profile profile) {
		StringBuffer message = new StringBuffer();

		message.append(LocalDate.now());
		message.append("<br/><br/>");
		message.append(profile.getFirstname() + "  " + profile.getLastname() + "<br/><br/>");
		message.append(profile.getAddress1() + "," + profile.getAddress2() + "<br/><br/>");
		message.append("Dear Mr./Ms " + profile.getLastname() + "," + "<br/><br/>");
		message.append(
				"Thank you for successfully uploading your application for Jamaican Citizenship by descent. Your application number "
						+ profile.getAppCode()
						+ " has been reviewed. Please proceed to select an appointment date from the list of available dates.");
		message.append("<br/>Please click the link below to schedule your appointment.<br/>");

		String schedulingUrl = link + "/";
		message.append("<br/><a href='" + schedulingUrl + "'>Schedule</a><br/>");

		message.append("Regards,<br/>");
		message.append("Passport, Immigration and Citizenship<br/>");
		message.append("Agency");
		return new String(message);

	}
}

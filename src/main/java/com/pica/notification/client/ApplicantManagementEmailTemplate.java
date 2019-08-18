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

		//TODO need to check, why link values is not populating from application.properties
		link="http://192.168.0.108:4200/login/scheduleAppointment";
		
		String schedulingUrl = link + "/";
		message.append("<br/><a href='" + schedulingUrl + "'>Schedule</a><br/>");

		message.append("Regards,<br/>");
		message.append("Passport, Immigration and Citizenship<br/>");
		message.append("Agency");
		return new String(message);

	}
	
	
	public static String confirmedAppointment(Profile profile) {
		StringBuffer message = new StringBuffer();

		message.append(LocalDate.now());
		message.append("<br/><br/>");
		message.append(profile.getFirstname() + "  " + profile.getLastname() + "<br/><br/>");
		message.append(profile.getAddress1() + "," + profile.getAddress2() + "<br/><br/>");
		message.append("<center><u>Appointment Confirmation</u></center><br/>");
		message.append("Dear Mr./Ms " + profile.getLastname() + "," + "<br/><br/>");
		message.append("Our records indicate that you are schedule for a Citizenship appointment.<br/><br/>");
		message.append("Your appointment has been confirmed for <b>"+profile.getDate() +" "+profile.getTime()+"</b> at the PICA office located at 8 Waterloo Road, Kingston 10. Please take your original documents and processing fee of $....... along with you to the interview.<br/><br/>");
		message.append("Please reply to info@pica.gov.jm confirm this appointment or call to reschedule at 876-754-7422<br/><br/><br/>");


		message.append("Regards,<br/>");
		message.append("Passport, Immigration and Citizenship<br/>");
		message.append("Agency");
		return new String(message);

	}
}

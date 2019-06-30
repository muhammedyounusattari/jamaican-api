package com.pica.mapper;

import java.time.LocalDate;
import java.util.Date;

import com.pica.model.DescentForm;
import com.pica.model.Profile;

public class EmailMessageTemplate {

	public static String getProfileMessageTemplate(Profile profile) {
		StringBuffer message = new StringBuffer();
		message.append("Thanks for submitting a profile, your username is "+profile.getEmail()+" and password is "+profile.getPassword());
		return new String(message);
	}

	public static String getDescentApplicationMessageTemplate(DescentForm descentForm) {
		StringBuffer message = new StringBuffer();
		Profile profile = descentForm.getProfile();
		
		message.append(LocalDate.now());
		message.append("<br/><br/>");
		message.append(profile.getFirstname()+"  "+profile.getLastname()+"<br/><br/>");
		message.append(profile.getAddress1()+","+profile.getAddress2()+"<br/><br/>");
		message.append("Dear Mr./Ms "+profile.getLastname()+","+"<br/><br/>");
		message.append("Thank you for submitting an application for Descent Application. Your application number is "+profile.getAppCode()+" which may be used to track the progress of your application. Your application and supporting document will be reviewed and an email update provided to you within 5 working days.<br/><br/>");
		message.append("Regards,<br/>");
		message.append("Passport, Immigration and Citizenship<br/>");
		message.append("Agency");
		return new String(message);
		
	}
	
	public static String getForgotPasswordMessageTemplate(String url) {
		StringBuffer message = new StringBuffer();
		message.append("You're receiving this e-mail because you requested a password reset for your Jamaican citizen ship account.<br/>");
		message.append("Please go to the following page and choose a new password:<br/>");
		message.append("<a href='"+url+"'>"+url+"</a><br/><br/>");
		message.append("Thanks,<br/>");
		message.append("Passport, Immigration and Citizenship<br/>");
		message.append("Agency");
		return new String(message);
	}

	
	
	
	/*
	public static String getMessageBodyForDescentForm(Profile profile) {
		StringBuffer message = new StringBuffer();
		return null;
	}



<Today’sDate>

<User FirstName LastName>

<User Address>

Dear Mr./Ms <FullName>

Thank you for submitting an application for <Application Submitted>. Your application number is 010100 which may be used to track the progress of your application. Your application and supporting document will be reviewed and an email update provided to you within 5 working days.

Regards,

Passport, Immigration and Citizenship

Agency
	
	*/
}
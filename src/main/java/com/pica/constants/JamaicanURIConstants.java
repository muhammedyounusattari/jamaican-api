package com.pica.constants;

public interface JamaicanURIConstants {

	public static final String HOME = "/v1/jamaican";
	public static final String ALL_FORMS = "/all-forms";
	public static final String CREATE_PROFILE = "/create-profile";
	public static final String SUBMIT_DESCENT_FORM = "/submit-descent-form";
	public static final String LOGIN = "/login";
	public static final String DESCENT_DOC_UPLOAD = "/descent-form-documents/{userId}/";
	public static final String VALIDATE_EMAIL = "/validate-email/{email}/";
	public static final String EMAIL_CHECK = "/email-check/{email}/";
	public static final String RESET_PASSWORD = "/reset-password";
	public static final String CHECK_STATUS = "/app-code-status/{appCode}/";
	public static final String GET_DESCENT_FORM = "/descent-form/{email}/";
	public static final String GET_DESCENT_FORM_DATA = "/app-code-status/";

	// Agent url's

	public static final String GET_FORMS_REVIEW = "/forms-review/{formType}/{type}";
	public static final String GET_AGENTS = "/load-agents/{formType}/{type}";
	public static final String ASSIGN_TO_AGENT = "/assign-to-agent";
	public static final String VALIDATE_AGENT = "/validate-agent";
	public static final String APPLICANT_DETAIL = "/applicant-detail/{applicantId}/{formType}";
	public static final String UPDATE_STATUS = "/update-applicant";
	public static final String AGENT_APPLICANTS = "/agent-applicant/{agentId}/{formType}";
	

	//Appointment
	
	public static final String SCHEDULE_APPOINTMENT = "/schedule-appointment";
	public static final String VALIDATE_BEFORE_APPOINTMENT = "/validate-schedule-appointment/{applicantId}/";
	public static final String VALIDATE_OFFICAL_LOGIN = "/validate-offical-login";
	
	
	//DeskClerk
	
	public static final String DC_APPLICANTS = "/deskclerk-applicant/{clerkId}/{formType}";
	public static final String GET_DESKCLERK = "/load-deskclerk/{formType}";
	public static final String ASSIGN_TO_DESKCLERK = "/assign-to-deskclerk";
	
	
	//Naturalisation form
	public static final String SUBMIT_NATURALISATION_FORM = "/submit-naturalisation-form";
	public static final String NATURRALISATION_FORM_UPLOAD = "/naturalisation-form-documents/{userId}/";
	
}

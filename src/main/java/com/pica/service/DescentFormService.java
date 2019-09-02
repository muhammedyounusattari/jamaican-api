package com.pica.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.pica.mapper.DescentFormHandler;
import com.pica.model.Agent;
import com.pica.model.AllForms;
import com.pica.model.Profile;
import com.pica.model.Roles;
import com.pica.model.Supervisor;
import com.pica.model.UploadDocuments;
import com.pica.payloads.AssignedApplicationPayload;
import com.pica.model.DescentForm;
import com.pica.model.DeskClerk;

public interface DescentFormService {

	List<AllForms> getAllPicaForms();

	Profile createPicaProfile(Profile createProfile);

	Profile authenticateUser(Profile createProfile);

	DescentForm submitDescentApplication(DescentFormHandler descentFormHandler);

	DescentForm uploadDescentDoc(String string, MultipartFile file);

	Profile validateEmailAddress(String email);
	
	Profile checkEmailAddress(String email);

	Profile resetPassword(Map<String, String> payload);

	Profile checkApplicationStatus(String appCode);

	DescentForm getDescentForm(String email);

	DescentForm getDescentFormData(Map<String, String> payload);

	List<?> getReviewForms(String formType,String type);

	List<Agent> getAgents(String formType);

	Supervisor assignApplicationToAgent(AssignedApplicationPayload payload);

	Agent getAgentDetails(Profile profile);

	List<?> getApplicantForm(String applicantId,String formType);

	Agent updateApplicantStatus(Map<String, String> payload);

	Profile scheduleAppointment(Map<String, String> payload);

	Profile validateAppointment(String applicantId);

	Roles validateOffical(Roles roles);

	Agent getAgentApplicants(String agentId, String formType);

	DeskClerk getDeskClerkApplicants(String agentId, String formType);

	List<DeskClerk> getDeskClerk(String formType);

	Supervisor assignApplicationToDeskClerk(AssignedApplicationPayload payload);

	DeskClerk updateApplicantStatusInDeskClerk(Map<String, String> payload);

	Object updateApplicantStatusInProfile(Map<String, String> payload);

}

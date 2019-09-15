package com.pica.web.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pica.mapper.DescentFormHandler;
import com.pica.model.Agent;
import com.pica.model.AllForms;
import com.pica.model.DescentForm;
import com.pica.model.DeskClerk;
import com.pica.model.Profile;
import com.pica.model.Roles;
import com.pica.model.Supervisor;
import com.pica.model.UploadDocuments;
import com.pica.payloads.AssignedApplicationPayload;
import com.pica.payloads.ResponsePayload;
import com.pica.service.DescentFormService;
import com.pica.web.DescentFormController;

import lombok.Data;

@RestController
public class DescentFormControllerImpl implements DescentFormController {

	private static final Logger LOG = LoggerFactory.getLogger(DescentFormControllerImpl.class);

	@Autowired
	private DescentFormService descentService;

	@Override
	public List<AllForms> getPicaForms() {

		LOG.info("getPicaForms() begins in DescentFormController");
		List<AllForms> picaForms = descentService.getAllPicaForms();
		return picaForms;
	}

	@Override
	public ResponseEntity<Profile> createUserProfile(@RequestBody Profile createProfile) {

		LOG.info("inside createUserProfile DescentFormController");
		Profile profile = descentService.createPicaProfile(createProfile);
		return new ResponseEntity<>(profile, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Profile> authenticateUser(@RequestBody Profile createProfile) {
		LOG.info("inside authenticateUser DescentFormController");
		Profile profile = descentService.authenticateUser(createProfile);
		return new ResponseEntity<>(profile, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DescentForm> submitDescentForm(@RequestBody DescentFormHandler descentFormHander) {

		LOG.info("inside submitDescentForm DescentFormController");
		DescentForm descentApplication = descentService.submitDescentApplication(descentFormHander);
		return new ResponseEntity<>(descentApplication, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> uploadFile(@PathVariable("userId") String userId, @RequestParam("file") MultipartFile file,
			@RequestParam("dataType") String dataType) {
		if (file.isEmpty())
			return new ResponseEntity<>("file date doesn't exsit", HttpStatus.BAD_REQUEST);

		DescentForm descentForm = descentService.uploadDescentDoc(userId, file);
		if (descentForm == null)
			return new ResponseEntity<>("payload missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(descentForm, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> validateEmail(String email) {
		if (email == null)
			return new ResponseEntity<>("email address missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Profile>(descentService.validateEmailAddress(email.toLowerCase()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> resetPassword(Map<String, String> payload) {

		return new ResponseEntity<Profile>(descentService.resetPassword(payload), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> checkEmail(String email) {
		if (email == null)
			return new ResponseEntity<>("email address missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Profile>(descentService.checkEmailAddress(email.toLowerCase()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> checkAppStatus(String appCode) {
		if (appCode == null)
			return new ResponseEntity<>("email address missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Profile>(descentService.checkApplicationStatus(appCode), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getDescentForm(String email) {
		if (email == null)
			return new ResponseEntity<>("email address missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<DescentForm>(descentService.getDescentForm(email), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getDescentFormData(Map<String, String> payload) {
		if (payload == null)
			return new ResponseEntity<>("payload is missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<DescentForm>(descentService.getDescentFormData(payload), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getFormForReview(String formType, String type) {
		if (formType == null)
			return new ResponseEntity<>("formType is missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<ResponsePayload>(
				new ResponsePayload(descentService.getReviewForms(formType, type), HttpStatus.OK), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAgentsList(String formType,String type) {
		return new ResponseEntity<ResponsePayload>(new ResponsePayload(descentService.getAgents(formType,type), HttpStatus.OK),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getDeskClerkList(String formType) {
		return new ResponseEntity<ResponsePayload>(new ResponsePayload(descentService.getDeskClerk(formType), HttpStatus.OK),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAssignedToAgent(AssignedApplicationPayload payload) {
		if (payload == null)
			return new ResponseEntity<>("payload is missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Supervisor>(descentService.assignApplicationToAgent(payload), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAssignedToDeskClerk(AssignedApplicationPayload payload) {
		if (payload == null)
			return new ResponseEntity<>("payload is missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Supervisor>(descentService.assignApplicationToDeskClerk(payload), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAgentValidate(Profile profile) {
		if (profile == null)
			return new ResponseEntity<>("request agent payload is missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Agent>(descentService.getAgentDetails(profile), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getApplicantDetails(String applicantId, String formType) {
		if (applicantId == null)
			return new ResponseEntity<>("applicantId is missing", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(this.descentService.getApplicantForm(applicantId, formType), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> updateApplicantStatus(Map<String, String> payload) {

		String type = payload.get("type");

		if (payload == null && type != null)
			return new ResponseEntity<String>("payload is missing", HttpStatus.BAD_REQUEST);

		if (type.equalsIgnoreCase("deskClerk"))
			return new ResponseEntity<DeskClerk>(descentService.updateApplicantStatusInDeskClerk(payload),
					HttpStatus.OK);
		else if (type.equalsIgnoreCase("agentView"))
			return new ResponseEntity<Agent>(descentService.updateApplicantStatus(payload), HttpStatus.OK);
		else
			return new ResponseEntity<ResponsePayload>(new ResponsePayload(descentService.updateApplicantStatusInProfile(payload),HttpStatus.OK),
					HttpStatus.OK);
//			return new ResponseEntity<>(new ResponsePayload(descentService.updateApplicantStatusInProfile(payload)),
//					HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> updateIncompleteApplicantStatus(Map<String, String> payload) {

		String type = payload.get("type");

		if (payload == null && type != null)
			return new ResponseEntity<String>("payload is missing", HttpStatus.BAD_REQUEST);

		if (type.equalsIgnoreCase("deskClerk"))
			return new ResponseEntity<DeskClerk>(descentService.updateApplicantStatusInDeskClerk(payload),
					HttpStatus.OK);
		else if (type.equalsIgnoreCase("agentView"))
			return new ResponseEntity<Agent>(descentService.updateApplicantStatus(payload), HttpStatus.OK);
		else
			return new ResponseEntity<ResponsePayload>(new ResponsePayload(descentService.updateApplicantStatusInProfile(payload),HttpStatus.OK),
					HttpStatus.OK);
//			return new ResponseEntity<>(new ResponsePayload(descentService.updateApplicantStatusInProfile(payload)),
//					HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> appointmentScheduled(Map<String, String> payload) {

		if (payload == null)
			return new ResponseEntity<>("payload is missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Profile>(descentService.scheduleAppointment(payload), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> isApplicableForScheduleAppointment(String applicantId) {

		if (applicantId == null)
			return new ResponseEntity<>("applicantId is missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Profile>(descentService.validateAppointment(applicantId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> validateOfficalLogin(Roles roles) {

		if (roles == null && roles.getUrl() != null && roles.getPassword() != null && roles.getLoginType() == null)
			return new ResponseEntity<>("applicantId is missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Roles>(descentService.validateOffical(roles), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAgentApplicants(String agentId, String formType) {

		if (agentId == null && formType == null) {
			return new ResponseEntity<>("applicantId is missing", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Agent>(descentService.getAgentApplicants(agentId, formType), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getDCApplicants(String agentId, String formType) {

		if (agentId == null && formType == null) {
			return new ResponseEntity<>("applicantId is missing", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<DeskClerk>(descentService.getDeskClerkApplicants(agentId, formType), HttpStatus.OK);
	}

	public static void main(String[] args) throws Exception {
		Map<String, Application> map = new HashMap<>();
		Application application1 = new Application("one", "teo", "three", "four", "five");
		map.put("dpa", application1);
		map.put("npa", application1);
		map.put("rr", application1);
		map.put("rrr", application1);

		List<Application> appList = new ArrayList<>();
		appList.add(application1);
		appList.add(application1);
		appList.add(application1);

		String str = new ObjectMapper().writeValueAsString(appList);

		// String str = new ObjectMapper().writeValueAsString(map);
		System.out.println(str);

	}

}

@Data
class Application {
	final String type;
	final String status;
	final String appCode;
	final String custId;
	final String base29Code;

	public Application(String type, String status, String appCode, String custId, String base29Code) {
		super();
		this.type = type;
		this.status = status;
		this.appCode = appCode;
		this.custId = custId;
		this.base29Code = base29Code;
	}

	public String getType() {
		return type;
	}

	public String getStatus() {
		return status;
	}

	public String getAppCode() {
		return appCode;
	}

	public String getCustId() {
		return custId;
	}

	public String getBase29Code() {
		return base29Code;
	}

}
package com.pica.web.impl;

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

import com.pica.mapper.DescentFormHandler;
import com.pica.model.Agent;
import com.pica.model.AllForms;
import com.pica.model.DescentForm;
import com.pica.model.Profile;
import com.pica.model.Supervisor;
import com.pica.model.UploadDocuments;
import com.pica.payloads.AssignedApplicationPayload;
import com.pica.payloads.ResponsePayload;
import com.pica.service.DescentFormService;
import com.pica.web.DescentFormController;

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
	public ResponseEntity<?> getFormForReview(String formType) {
		if (formType == null)
			return new ResponseEntity<>("formType is missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<ResponsePayload>(
				new ResponsePayload(descentService.getReviewForms(formType), HttpStatus.OK), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAgentsList() {
		return new ResponseEntity<ResponsePayload>(new ResponsePayload(descentService.getAgents(), HttpStatus.OK),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAssignedToAgent(AssignedApplicationPayload payload) {
		if (payload == null)
			return new ResponseEntity<>("payload is missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Supervisor>(descentService.assignApplicationToAgent(payload), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAgentValidate(Profile profile) {
		if (profile == null)
			return new ResponseEntity<>("request agent payload is missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Agent>(descentService.getAgentDetails(profile), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getApplicantDetails(String applicantId) {
		if (applicantId == null)
			return new ResponseEntity<>("applicantId is missing", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<DescentForm>(this.descentService.getApplicantForm(applicantId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> updateApplicantStatus(Map<String, String> payload) {

		if (payload == null)
			return new ResponseEntity<>("payload is missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Agent>(descentService.updateApplicantStatus(payload), HttpStatus.OK);
	}
}

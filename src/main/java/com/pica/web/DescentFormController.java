package com.pica.web;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pica.constants.DescentFormConstantsURIConstants;
import com.pica.mapper.DescentFormHandler;
import com.pica.model.AllForms;
import com.pica.model.Profile;
import com.pica.model.DescentForm;

@RequestMapping(value=DescentFormConstantsURIConstants.HOME)
public interface DescentFormController {
	
	@RequestMapping(value=DescentFormConstantsURIConstants.ALL_FORMS, method = RequestMethod.GET)
	public List<AllForms> getPicaForms();

	@RequestMapping(value=DescentFormConstantsURIConstants.CREATE_PROFILE, method= RequestMethod.POST)
	public ResponseEntity<?> createUserProfile(Profile createProfile);
	
	@RequestMapping(value=DescentFormConstantsURIConstants.LOGIN, method=RequestMethod.POST)
	public ResponseEntity<?> authenticateUser(Profile createProfile);
	
	@RequestMapping(value=DescentFormConstantsURIConstants.SUBMIT_DESCENT_FORM, method= RequestMethod.POST)
	public ResponseEntity<?> submitDescentForm(DescentFormHandler descentForm);
	
	@RequestMapping(value = DescentFormConstantsURIConstants.DESCENT_DOC_UPLOAD,headers = "content-type=multipart/form-data",method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(@PathVariable("userId") String userId, @RequestParam("file") MultipartFile file, @RequestParam("dataType") String dataType);

	@RequestMapping(value = DescentFormConstantsURIConstants.VALIDATE_EMAIL, method=RequestMethod.GET)
	public ResponseEntity<?> validateEmail(@PathVariable("email") String email);
	
	@RequestMapping(value = DescentFormConstantsURIConstants.EMAIL_CHECK, method=RequestMethod.GET)
	public ResponseEntity<?> checkEmail(@PathVariable("email") String email);
	
	@RequestMapping(value = DescentFormConstantsURIConstants.RESET_PASSWORD,method=RequestMethod.POST)
	public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> payload);
	
	@RequestMapping(value = DescentFormConstantsURIConstants.CHECK_STATUS, method=RequestMethod.GET)
	public ResponseEntity<?> checkAppStatus(@PathVariable("appCode") String appCode);
	
	@RequestMapping(value = DescentFormConstantsURIConstants.GET_DESCENT_FORM, method=RequestMethod.GET)
	public ResponseEntity<?> getDescentForm(@PathVariable("email") String email);
	
	@RequestMapping(value = DescentFormConstantsURIConstants.GET_DESCENT_FORM_DATA, method=RequestMethod.POST)
	public ResponseEntity<?> getDescentFormData(@RequestBody Map<String,String> payload);
}

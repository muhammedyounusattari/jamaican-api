package com.pica.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
}

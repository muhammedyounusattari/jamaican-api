package com.pica.web.impl;

import java.util.List;

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
import com.pica.model.AllForms;
import com.pica.model.DescentForm;
import com.pica.model.Profile;
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
		List<AllForms> picaForms  = descentService.getAllPicaForms();
		return picaForms;
	}

	@Override
	public ResponseEntity<Profile> createUserProfile(@RequestBody Profile createProfile) {
		
		LOG.info("inside createUserProfile DescentFormController");
		Profile profile = descentService.createPicaProfile(createProfile);
		return new ResponseEntity<>(profile,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Profile> authenticateUser(@RequestBody Profile createProfile) {
		LOG.info("inside authenticateUser DescentFormController");
		Profile profile = descentService.authenticateUser(createProfile);
		return new ResponseEntity<>(profile,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DescentForm> submitDescentForm(@RequestBody DescentFormHandler descentFormHander) {
		
		LOG.info("inside submitDescentForm DescentFormController");
		DescentForm descentApplication = descentService.submitDescentApplication(descentFormHander);
		return new ResponseEntity<>(descentApplication,HttpStatus.OK);
	}

	@Override
    public ResponseEntity<?> uploadFile(@PathVariable("userId") String userId, @RequestParam("file") MultipartFile file, @RequestParam("dataType") String dataType) {
		if(file.isEmpty())
			return new ResponseEntity<>("Found Ghost",HttpStatus.BAD_GATEWAY);
		
		DescentForm descentForm = descentService.uploadDescentDoc(userId,file);
		if(descentForm==null)
			return new ResponseEntity<>("Found Ghost",HttpStatus.BAD_GATEWAY);
		
		return new ResponseEntity<>(descentForm,HttpStatus.OK);
    }
}

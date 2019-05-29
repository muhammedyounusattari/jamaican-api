package com.pica.web.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pica.model.AllForms;
import com.pica.model.Profile;
import com.pica.model.DescentForm;
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
	public Profile createUserProfile(@RequestBody Profile createProfile) {
		
		LOG.info("inside createUserProfile DescentFormController");
		Profile picaProfile = descentService.createPicaProfile(createProfile);
		return picaProfile;
	}

	@Override
	public Profile authenticateUser(@RequestBody Profile createProfile) {
		
		LOG.info("inside authenticateUser DescentFormController");
		Profile authetic = descentService.authenticateUser(createProfile);
		return authetic;
	}

	@Override
	public DescentForm submitDescentForm(@RequestBody DescentForm descentForm) {
		
		LOG.info("inside submitDescentForm DescentFormController");
		DescentForm descentApplication = descentService.submitDescentApplication(descentForm);
		return descentApplication;
	}

}

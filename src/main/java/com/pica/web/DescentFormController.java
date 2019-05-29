package com.pica.web;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pica.constants.DescentFormConstantsURIConstants;
import com.pica.model.AllForms;
import com.pica.model.Profile;
import com.pica.model.DescentForm;

@RequestMapping(value=DescentFormConstantsURIConstants.HOME)
public interface DescentFormController {
	
	@RequestMapping(value=DescentFormConstantsURIConstants.ALL_FORMS, method = RequestMethod.GET)
	public List<AllForms> getPicaForms();

	@RequestMapping(value=DescentFormConstantsURIConstants.CREATE_PROFILE, method= RequestMethod.POST)
	public Profile createUserProfile(Profile createProfile);
	
	@RequestMapping(value=DescentFormConstantsURIConstants.LOGIN, method=RequestMethod.POST)
	public Profile authenticateUser(Profile createProfile);
	
	@RequestMapping(value=DescentFormConstantsURIConstants.SUBMIT_DESCENT_FORM, method= RequestMethod.POST)
	public DescentForm submitDescentForm(DescentForm descentForm);
	
	
}

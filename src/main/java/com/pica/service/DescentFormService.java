package com.pica.service;

import java.util.List;

import com.pica.model.AllForms;
import com.pica.model.Profile;
import com.pica.model.DescentForm;

public interface DescentFormService {

	List<AllForms> getAllPicaForms();

	Profile createPicaProfile(Profile createProfile);

	Profile authenticateUser(Profile createProfile);

	DescentForm submitDescentApplication(DescentForm descentForm);

}

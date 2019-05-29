package com.pica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pica.dao.AllFormsDAO;
import com.pica.dao.CreateProfileDAO;
import com.pica.dao.DescentFormDAO;
import com.pica.model.AllForms;
import com.pica.model.Profile;
import com.pica.model.DescentForm;
import com.pica.service.DescentFormService;

@Service
public class DescentFormServiceImpl  implements DescentFormService {

	@Autowired
	private CreateProfileDAO createProfileDAO;
	
	@Autowired
	private DescentFormDAO descentFormDAO;
	
	@Autowired
	private AllFormsDAO allFormsDAO;
	
	@Override
	public List<AllForms> getAllPicaForms() {
		List<AllForms> allForms = allFormsDAO.findAll();
		return allForms;
	}

	@Override
	public Profile createPicaProfile(Profile createProfile) {
		Profile profile = createProfileDAO.save(createProfile);
		return profile;
	}

	@Override
	public Profile authenticateUser(Profile createProfile) {
//		createProfileDAO.
		return null;
	}

	@Override
	public DescentForm submitDescentApplication(DescentForm descentForm) {
		DescentForm descentApp = descentFormDAO.save(descentForm);
		return descentApp;
	}

}

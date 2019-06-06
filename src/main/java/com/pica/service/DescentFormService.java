package com.pica.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pica.mapper.DescentFormHandler;
import com.pica.model.AllForms;
import com.pica.model.Profile;
import com.pica.model.DescentForm;

public interface DescentFormService {

	List<AllForms> getAllPicaForms();

	Profile createPicaProfile(Profile createProfile);

	Profile authenticateUser(Profile createProfile);

	DescentForm submitDescentApplication(DescentFormHandler descentFormHandler);

	DescentForm uploadDescentDoc(String string, MultipartFile file);

}

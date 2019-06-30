package com.pica.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
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

	Profile validateEmailAddress(String email);
	
	Profile checkEmailAddress(String email);

	Profile resetPassword(Map<String, String> payload);

	Profile checkApplicationStatus(String appCode);

	DescentForm getDescentForm(String email);

	DescentForm getDescentFormData(Map<String, String> payload);

}

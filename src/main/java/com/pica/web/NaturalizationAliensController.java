package com.pica.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.pica.constants.JamaicanURIConstants;
import com.pica.dto.NaturalizationAliensDto;
import com.pica.model.natural.alien.NaturalizationAliens;

@RequestMapping(value = JamaicanURIConstants.HOME)
public interface NaturalizationAliensController {

	
	@RequestMapping(value = JamaicanURIConstants.SUBMIT_NATURALISATION_FORM, method = RequestMethod.POST)
	ResponseEntity<?> submitNaturalizationAliens(@RequestBody NaturalizationAliensDto naturalizationAliens);

	@RequestMapping(value= JamaicanURIConstants.NATURRALISATION_FORM_UPLOAD , method = RequestMethod.POST)
	ResponseEntity<?> uploadFile(String userId, MultipartFile file, String dataType);

}

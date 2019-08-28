package com.pica.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.pica.constants.JamaicanURIConstants;
import com.pica.mapper.NaturalizationAliensHandler;
@RequestMapping(value = JamaicanURIConstants.HOME)
public interface NaturalizationAliensController {
	
	@RequestMapping(value = JamaicanURIConstants.SUBMIT_NATURALISATION_FORM, method = RequestMethod.POST)
	ResponseEntity<?> submitNaturalizationAliens(NaturalizationAliensHandler naturalizationAliens);

	@RequestMapping(value = JamaicanURIConstants.GET_NATURAL_FORM, method = RequestMethod.GET)
	public ResponseEntity<?> getNaturalisationForm(@PathVariable("email") String email);
	
	@RequestMapping(value = "/bhjbvh", method = RequestMethod.POST)
	ResponseEntity<?> uploadFile(String userId, MultipartFile file, String dataType);
	

}

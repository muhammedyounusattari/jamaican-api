package com.pica.web;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pica.constants.JamaicanURIConstants;
import com.pica.mapper.NaturalizationAliensHandler;
@RequestMapping(value = JamaicanURIConstants.HOME)
public interface NaturalizationAliensController {
	
	@RequestMapping(value = JamaicanURIConstants.SUBMIT_NATURALISATION_FORM, method = RequestMethod.POST)
	ResponseEntity<?> submitNaturalizationAliens(NaturalizationAliensHandler naturalizationAliens);

	@RequestMapping(value = JamaicanURIConstants.GET_NATURAL_FORM, method = RequestMethod.GET)
	public ResponseEntity<?> getNaturalisationForm(@PathVariable("email") String email);
	
	@RequestMapping(value = JamaicanURIConstants.NATURALISATION_FORM_UPLOAD,headers = "content-type=multipart/form-data", method = RequestMethod.POST)
	ResponseEntity<?> uploadFile(@PathVariable("userId") String userId, @RequestParam("file") MultipartFile file, @RequestParam("dataType") String dataType);
	

}

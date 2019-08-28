package com.pica.web.impl;

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
import com.pica.mapper.NaturalizationAliensHandler;
import com.pica.model.DescentForm;
import com.pica.model.natural.alien.NaturalizationAliens;
import com.pica.service.NaturalizationAliensService;
import com.pica.web.NaturalizationAliensController;

@RestController
public class NaturalizationAliensControllerImpl implements NaturalizationAliensController {

	private static final Logger LOG = LoggerFactory.getLogger(NaturalizationAliensControllerImpl.class);

	@Autowired
	private NaturalizationAliensService naturalizationService;

	@Override
	public ResponseEntity<?> submitNaturalizationAliens(@RequestBody NaturalizationAliensHandler alienFormHander) {
		LOG.info("inside submitNaturalizationAliens NaturalizationAliensControllerImpl");
		if (alienFormHander == null)
			return new ResponseEntity<>("request payload is missing", HttpStatus.BAD_REQUEST);

		NaturalizationAliens naturializationAliens = naturalizationService
				.submitNaturalizationAliens(alienFormHander);
		return new ResponseEntity<>(naturializationAliens, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> uploadFile(@PathVariable("userId") String userId, @RequestParam("file") MultipartFile file,
			@RequestParam("dataType") String dataType) {
		if (file.isEmpty())
			return new ResponseEntity<>("file date doesn't exsit", HttpStatus.BAD_REQUEST);

		NaturalizationAliens naturializationAliens = naturalizationService.uploadNaturalAlientDoc(userId, file);
		if (naturializationAliens == null)
			return new ResponseEntity<>("payload missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(naturializationAliens, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getNaturalisationForm(String email) {
		if (email == null)
			return new ResponseEntity<>("email address missing", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<NaturalizationAliens>(naturalizationService.getNaturalisationForm(email), HttpStatus.OK);
	}
	

	
}

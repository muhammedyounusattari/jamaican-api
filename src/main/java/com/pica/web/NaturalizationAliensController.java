package com.pica.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.pica.model.natural.alien.NaturalizationAliens;

public interface NaturalizationAliensController {

	ResponseEntity<?> submitNaturalizationAliens(NaturalizationAliens naturalizationAliens);

	ResponseEntity<?> uploadFile(String userId, MultipartFile file, String dataType);

}

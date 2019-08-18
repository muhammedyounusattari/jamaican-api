package com.pica.service;

import org.springframework.web.multipart.MultipartFile;

import com.pica.model.natural.alien.NaturalizationAliens;

public interface NaturalizationAliensService {

	NaturalizationAliens submitNaturalizationAliens(NaturalizationAliens naturalizationAliens);

	NaturalizationAliens uploadNaturalAlientDoc(String userId, MultipartFile file);

}

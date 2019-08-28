package com.pica.service;

import org.springframework.web.multipart.MultipartFile;

import com.pica.mapper.NaturalizationAliensHandler;
import com.pica.model.DescentForm;
import com.pica.model.natural.alien.NaturalizationAliens;

public interface NaturalizationAliensService {

	

	NaturalizationAliens uploadNaturalAlientDoc(String userId, MultipartFile file);

	NaturalizationAliens submitNaturalizationAliens(NaturalizationAliensHandler naturalizationAliensDTO);

	NaturalizationAliens getNaturalisationForm(String email);

}

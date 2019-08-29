package com.pica.service;

import org.springframework.web.multipart.MultipartFile;

import com.pica.dto.NaturalizationAliensDto;
import com.pica.model.natural.alien.NaturalizationAliens;

public interface NaturalizationAliensService {

	NaturalizationAliens submitNaturalizationAliens(NaturalizationAliensDto naturalizationAliensDto);

	NaturalizationAliens uploadNaturalAlientDoc(String userId, MultipartFile file);

}

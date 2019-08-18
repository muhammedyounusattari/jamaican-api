package com.pica.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pica.model.DescentForm;
import com.pica.model.natural.alien.NaturalizationAliens;

@Repository
public interface NaturalizationAliensDAO extends MongoRepository<NaturalizationAliens, Long>{

	NaturalizationAliens findByProfileEmail(String email);

}

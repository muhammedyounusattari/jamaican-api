package com.pica.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pica.model.natural.alien.NaturalizationAliens;

@Repository
public interface NaturalizationAliensDAO extends MongoRepository<NaturalizationAliens, Long>{

	@Query(value = "{ 'profile.email' : ?0 }")
	NaturalizationAliens findByProfileEmail(String email);

}

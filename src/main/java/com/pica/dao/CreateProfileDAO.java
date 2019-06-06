package com.pica.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pica.model.Profile;

@Repository
public interface CreateProfileDAO extends MongoRepository<Profile, Long>{
	
	Profile findByEmailAndPassword(String email,String password);
	Profile findByEmail(String email);
	
}

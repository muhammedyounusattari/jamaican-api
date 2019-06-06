package com.pica.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pica.model.DescentForm;

@Repository
public interface DescentFormDAO extends MongoRepository<DescentForm, Long> {

	@Query(value = "{ 'profile.email' : ?0 }")
	List<DescentForm> findByProfileEmail(String email);

}

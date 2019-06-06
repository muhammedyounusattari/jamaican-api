package com.pica.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pica.model.AllForms;

@Repository
public interface AllFormsDAO extends MongoRepository<AllForms, Long>{
	
}

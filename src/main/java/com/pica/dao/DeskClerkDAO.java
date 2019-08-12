package com.pica.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pica.model.Agent;
import com.pica.model.DeskClerk;

@Repository
public interface DeskClerkDAO extends MongoRepository<DeskClerk, Long>{

	@Query(value="{'_id':?0}")
	DeskClerk findBy_Id(Integer id);

	DeskClerk findBy_idAndFormType(int parseInt, String formType);
}

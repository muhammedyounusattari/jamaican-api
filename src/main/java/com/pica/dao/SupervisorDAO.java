package com.pica.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pica.model.Agent;
import com.pica.model.Supervisor;

@Repository
public interface SupervisorDAO extends MongoRepository<Supervisor, Long> {

//	@Query("{agent._id:?0")
	@Query(value = "{ 'agent._id' : ?0 }")
	public Supervisor findByAgent_id(Integer id);
	
	@Query(value = "{ 'deskClerk._id' : ?0 }")
	public Supervisor findByDeskClerk_id(Integer id);
}

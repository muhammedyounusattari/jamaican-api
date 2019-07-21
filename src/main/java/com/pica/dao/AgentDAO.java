package com.pica.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pica.model.Agent;

@Repository
public interface AgentDAO extends MongoRepository<Agent, Long>{

	@Query(value="{'_id':?0}")
	Agent findBy_Id(Integer id);
}

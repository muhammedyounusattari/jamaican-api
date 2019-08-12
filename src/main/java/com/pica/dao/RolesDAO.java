package com.pica.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pica.model.Roles;

@Repository
public interface RolesDAO extends MongoRepository<Roles, Long> {

	Roles findByUserIdAndPassword(String integer,String password);

	Roles findByUserId(String agentId);
}

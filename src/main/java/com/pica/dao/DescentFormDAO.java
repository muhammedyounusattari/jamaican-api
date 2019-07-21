package com.pica.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pica.model.DescentForm;

@Repository
public interface DescentFormDAO extends MongoRepository<DescentForm, Long> {

	@Query(value = "{ 'profile.email' : ?0 }")
	DescentForm findByProfileEmail(String email);

	@Query(value = "{'profile.appCode':?0}")
	DescentForm findByAppCode(String appCode);

	@Query(value="{'appCode':?0,'profile.dob':?1}")
	DescentForm findByAppCodeAndDOB(String appCode, String dob);
	
	@Query(value="{'_id':{$in:?0}}")
	List<DescentForm> findBy_id(List<Integer> list);
	
	@Query(value="{'_id':?0}")
	DescentForm findBy_id(Integer applicantId);
}

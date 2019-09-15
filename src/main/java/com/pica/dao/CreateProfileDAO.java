package com.pica.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pica.commons.FormStatus;
import com.pica.model.Profile;

@Repository
public interface CreateProfileDAO extends MongoRepository<Profile, Long>{
	
	Profile findByEmailAndPassword(String email,String password);
	Profile findByEmail(String email);
	
	@Query(value="{'applied':?0, 'status':{$in:?1}}")
	List<Profile> findByAppliedAndStatus(String applied,List<String> status);
	
	Profile findByCustId(String custId);
	
	@Query(value="{'_id':?0}")
	Profile findBy_Id(Integer _id);
	
	
	Profile findByAppCode(String applicantId);
	
	@Query(value="{'appCode':{$in:?0}}, 'status':?1")
	List<Profile> findByAppCodeAndStatus(List<Integer> appCode, String status);
	
	Profile findByAppCodeAndStatus(Integer applicantId, String byValue);
	
	Profile findByAppCodeAndStatus(String applicantId, String byValue);
	
	Profile findByAppCodeAndStatusAndApplied(String applicantId, String byValue,String applied);
}

package com.pica.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pica.model.DescentForm;
import com.pica.model.natural.alien.NaturalizationAliens;

@Repository
public interface NaturalizationAliensDAO extends MongoRepository<NaturalizationAliens, Long> {

	NaturalizationAliens findByProfileEmail(String email);

	@Query(value = "{'_id':{$in:?0}}")
	List<NaturalizationAliens> findBy_id(List<Integer> list);
	
	@Query(value="{'_id':?0}")
	NaturalizationAliens findBy_id(Integer applicantId);
	
	@Query(value = "{'profile.appCode':?0}")
	NaturalizationAliens findByAppCode(String applicantId);

}

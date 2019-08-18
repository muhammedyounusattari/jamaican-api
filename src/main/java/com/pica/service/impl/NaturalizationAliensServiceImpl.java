package com.pica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pica.dao.CreateProfileDAO;
import com.pica.dao.NaturalizationAliensDAO;
import com.pica.dao.SequenceDAO;
import com.pica.mapper.DescentFormMapper;
//import com.pica.mapper.NaturalizationAliensMapper;
import com.pica.model.DescentForm;
import com.pica.model.Profile;
import com.pica.model.natural.alien.NaturalizationAliens;
import com.pica.service.NaturalizationAliensService;

@Service
public class NaturalizationAliensServiceImpl implements NaturalizationAliensService{

	@Autowired
	private NaturalizationAliensDAO naturalizationDAO;
	
	@Autowired
	private CreateProfileDAO profileDAO;
	
	@Autowired
	private SequenceDAO sequenceDAO;
	
	@Override
	public NaturalizationAliens submitNaturalizationAliens(NaturalizationAliens naturalizationAliensDTO) {

		 
	/*	NaturalizationAliens naturalizationAliensDb = naturalizationDAO.findByProfileEmail(naturalizationAliensDTO.getProfile().getEmail());

		Profile profileDb = profileDAO.findByEmail(naturalizationAliensDTO.getProfile().getEmail());
		profileDb = NaturalizationAliensMapper.syncProfileForm(naturalizationAliensDTO.getProfile(), profileDb);

		if (naturalizationAliensDb != null) {
			naturalizationAliensDb.setProfile(profileDb);
			naturalizationAliensDb = NaturalizationAliensMapper.syncNaturalAlienForm(naturalizationAliensDTO, naturalizationAliensDb);
			profileDAO.save(naturalizationAliensDb.getProfile());
			return naturalizationDAO.save(naturalizationAliensDb);
		}

		descentForm.setId((int) sequenceDAO.getNextSequenceIdProfile(HOSTING));
		// profileDb.setPassword(descentFormHandler.getPassword());
		descentForm.setProfile(profileDAO.save(profileDb));
		return descentFormDAO.save(descentForm);
*/
		return null;
	}

	@Override
	public NaturalizationAliens uploadNaturalAlientDoc(String userId, MultipartFile file) {
		
		return null;
	}

}

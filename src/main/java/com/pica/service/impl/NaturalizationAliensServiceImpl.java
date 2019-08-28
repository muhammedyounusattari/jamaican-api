package com.pica.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pica.commons.PICAApplictions;
import com.pica.dao.CreateProfileDAO;
import com.pica.dao.DescentFormDAO;
import com.pica.dao.NaturalizationAliensDAO;
import com.pica.dao.SequenceDAO;
import com.pica.mapper.NaturalizationAliensHandler;
import com.pica.mapper.NaturalizationAliensMapper;
import com.pica.model.DescentForm;
import com.pica.model.Forms;
import com.pica.model.Profile;
import com.pica.model.natural.alien.NaturalizationAliens;
import com.pica.service.NaturalizationAliensService;

@Service
public class NaturalizationAliensServiceImpl implements NaturalizationAliensService{

	
	private static final Logger LOG = LoggerFactory.getLogger(NaturalizationAliensServiceImpl.class);
	@Autowired
	private NaturalizationAliensDAO naturalizationDAO;
	@Autowired
	private DescentFormDAO descentFormDAO;
	
	@Autowired
	private CreateProfileDAO profileDAO;
	
	@Autowired
	private SequenceDAO sequenceDAO;
	
	private String HOSTING = "hosting";
	
	@Override
	public NaturalizationAliens submitNaturalizationAliens(NaturalizationAliensHandler naturalizationAliensDTO) {

		NaturalizationAliens aliensForm = NaturalizationAliensMapper.formatPayload(naturalizationAliensDTO);
		NaturalizationAliens aliensFormDb = naturalizationDAO.findByProfileEmail(aliensForm.getProfile().getEmail());
		Profile profileDb = profileDAO.findByEmail(aliensForm.getProfile().getEmail());
		profileDb = NaturalizationAliensMapper.syncProfileForm(aliensForm.getProfile(), profileDb);
		
		
				if (aliensFormDb != null) {
					aliensFormDb.setProfile(profileDb);
					aliensForm = NaturalizationAliensMapper.syncNaturalAlienForm(aliensForm, aliensFormDb);
					
					profileDAO.save(aliensForm.getProfile());
		return naturalizationDAO.save(aliensForm);
	}
				aliensForm.set_id((int) sequenceDAO.getNextSequenceIdProfile(HOSTING));
				ArrayList<Forms> forms = new ArrayList<Forms>();
				Forms forms1 = new Forms();
				forms1.setFormAppCode(Integer.valueOf(aliensForm.get_id()).toString());
				forms1.setFormStatus("saved");
				forms1.setFormName(PICAApplictions.NACW.getApplication());
				forms.add(forms1);
				
				
				if(profileDb.getForms() != null) {
					 profileDb.getForms().addAll(forms);
					 
				}else {
					 profileDb.setForms(forms );
				}
				aliensForm.setProfile(profileDAO.save(profileDb));
				return naturalizationDAO.save(aliensForm);
		
	}


	
	
	
	@Override
	public NaturalizationAliens uploadNaturalAlientDoc(String userId, MultipartFile file) {
		
		return null;
	}

	@Override
	public NaturalizationAliens getNaturalisationForm(String email) {
		return naturalizationDAO.findByProfileEmail(email);
	}
	


}

package com.pica.mapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.pica.model.ApplicantDocument;
import com.pica.model.Profile;
import com.pica.model.natural.alien.NaturalizationAliens;

public class NaturalizationAliensMapper {

	private static String UPLOADED_FOLDER = "/Users/MuhammedYounusAttari/git/jamaican-citizenship-ui/Kastech workspace/jamaican-citizenship-ui/src/assets/documents";
			//"/Users/admin/Downloads/Kastech workspace/jamaican-citizenship-ui/Kastech workspace/jamaican-citizenship-ui/src/assets/documents/";

	private static final Logger LOG = LoggerFactory.getLogger(NaturalizationAliensMapper.class);
	
	public static Profile syncProfileForm(Profile profile, Profile profileDb) {

		profileDb.setAddress1(profile.getAddress1());
		profileDb.setAddress2(profile.getAddress2());
		profileDb.setZip(profile.getZip());
		profileDb.setCountry(profile.getCountry());

		profileDb.setFirstname(profile.getFirstname());
		profileDb.setLastname(profile.getLastname());
		profileDb.setGender(profile.getGender());
		profileDb.setDob(profile.getDob());
		profileDb.setNumber(profile.getNumber());
		profileDb.setPob(profile.getPob());
		// profileDb.setPassword(profile.getPassword());
		// profileDb.setPasswordChanged(profile.isPasswordChanged());

		return profileDb;
	}

	public static NaturalizationAliens syncNaturalAlienForm(NaturalizationAliens naturalizationAliensDTO,
			NaturalizationAliens naturalizationAliensDb) {
		
		naturalizationAliensDb.setProfile(syncProfileForm(naturalizationAliensDTO.getProfile(), naturalizationAliensDb.getProfile()));
		naturalizationAliensDb.setSection1(naturalizationAliensDTO.getSection1());
		naturalizationAliensDb.setSection2(naturalizationAliensDTO.getSection2());
		naturalizationAliensDb.setSection3(naturalizationAliensDTO.getSection3());
		naturalizationAliensDb.setSection4(naturalizationAliensDTO.getSection4());
		naturalizationAliensDb.setSection5(naturalizationAliensDTO.getSection5());
		naturalizationAliensDb.setSection6(naturalizationAliensDTO.getSection6());
		naturalizationAliensDb.setSection7(naturalizationAliensDTO.getSection7());
		naturalizationAliensDb.setSection8(naturalizationAliensDTO.getSection8());
		naturalizationAliensDb.setSection9(naturalizationAliensDTO.getSection9());
		naturalizationAliensDb.setSection10(naturalizationAliensDTO.getSection10());
		naturalizationAliensDb.setAccept(naturalizationAliensDTO.getAccept());
		
		return naturalizationAliensDb;
	}
	
	public static ApplicantDocument saveDocument(String location, MultipartFile file) {

		ApplicantDocument applicantDoc = new ApplicantDocument();

		String savedLoc = "/assets/documents/" + location + "/";
		location = UPLOADED_FOLDER + location + "/";

		byte[] bytes;
		try {
			File locate = new File(location);
			locate.mkdir();
			bytes = file.getBytes();
			Path path = Paths.get(location + file.getOriginalFilename());
			Files.write(path, bytes);

			applicantDoc.setFileName(file.getOriginalFilename());
			applicantDoc.setFilePath(savedLoc + file.getOriginalFilename());

			LOG.info("file location " + location);
			LOG.info("file name " + file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
			return null;

		}
		return applicantDoc;
	}
	
	
}

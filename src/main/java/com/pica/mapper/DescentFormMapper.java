package com.pica.mapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import com.pica.model.Dependents;
import com.pica.model.DescentForm;
import com.pica.model.Profile;

public class DescentFormMapper {

//	Dependents dependents = null;
	
	private static String UPLOADED_FOLDER = "/Users/admin/Documents/pica/";

	public static DescentForm formatPayload(DescentFormHandler descentFormHander) {

		DescentForm descentForm = new DescentForm();
//		descentForm.setId(id);

		Profile profile = getUserProfile(descentFormHander);
		Dependents father = getFatherDetails(descentFormHander);
		Dependents mother = getMotherDetails(descentFormHander);
		Dependents paternalFather = getPaternalFatherDetails(descentFormHander);
		Dependents paternalMother = getPaternalMotherDetails(descentFormHander);
		Dependents maternalFather = getMaternalFatherDetails(descentFormHander);
		Dependents maternalMother = getMaternalMotherDetails(descentFormHander);

		descentForm.setProfile(profile);
		descentForm.setFather(father);
		descentForm.setMother(mother);
		descentForm.setPaternalFather(paternalFather);
		descentForm.setPaternalMother(paternalMother);
		descentForm.setMaternalFather(maternalFather);
		descentForm.setMaternalMother(maternalMother);

		return descentForm;
	}

	private static Profile getUserProfile(DescentFormHandler descentFormHander) {
		Profile profile = new Profile();
		profile.setId(descentFormHander.getId());
		profile.setFirstname(descentFormHander.getFirstname());
		profile.setLastname(descentFormHander.getLastname());
		profile.setMiddlename(descentFormHander.getMiddlename());
		profile.setAddress(descentFormHander.getAddress());
		profile.setDob(descentFormHander.getDob());
		profile.setNumber(descentFormHander.getNumber());
		profile.setEmail(descentFormHander.getEmail());
		profile.setGender(descentFormHander.getGender());
		profile.setPob(descentFormHander.getPob());
		
		return profile;
}

	private static Dependents getPaternalMotherDetails(DescentFormHandler descentFormHander) {
		Dependents dependents = new Dependents();
		dependents.setFirstName(descentFormHander.getPfirstname2());
		dependents.setLastName(descentFormHander.getPlastname2());
		dependents.setCob(descentFormHander.getPpob2());
		dependents.setDob(descentFormHander.getPdob2());
		return dependents;
	}

	private static Dependents getPaternalFatherDetails(DescentFormHandler descentFormHander) {
		Dependents dependents = new Dependents();
		dependents.setFirstName(descentFormHander.getPfirstname1());
		dependents.setLastName(descentFormHander.getPlastname1());
		dependents.setCob(descentFormHander.getPpob1());
		dependents.setDob(descentFormHander.getPdob1());
		return dependents;
	}

	private static Dependents getMaternalMotherDetails(DescentFormHandler descentFormHander) {
		Dependents dependents = new Dependents();
		dependents.setFirstName(descentFormHander.getMfirstname2());
		dependents.setLastName(descentFormHander.getMlastname2());
		dependents.setCob(descentFormHander.getMpob2());
		dependents.setDob(descentFormHander.getMdob2());
		return dependents;
	}

	private static Dependents getMaternalFatherDetails(DescentFormHandler descentFormHander) {
		Dependents dependents = new Dependents();
		dependents.setFirstName(descentFormHander.getPfirstname1());
		dependents.setLastName(descentFormHander.getPlastname1());
		dependents.setCob(descentFormHander.getPpob1());
		dependents.setDob(descentFormHander.getPdob1());
		return dependents;
	}

	private static Dependents getMotherDetails(DescentFormHandler descentFormHander) {
		Dependents dependents = new Dependents();
		dependents.setFirstName(descentFormHander.getMfirstname());
		dependents.setLastName(descentFormHander.getMlastname());
		dependents.setCob(descentFormHander.getMpob());
		dependents.setDob(descentFormHander.getMdob());
		return dependents;
	}

	private static Dependents getFatherDetails(DescentFormHandler descentFormHander) {

		Dependents dependents = new Dependents();
		dependents.setFirstName(descentFormHander.getFfirstname());
		dependents.setLastName(descentFormHander.getFlastname());
		dependents.setCob(descentFormHander.getFpob());
		dependents.setDob(descentFormHander.getFdob());
		return dependents;
	
	}

	public static Boolean saveDocument(String location, MultipartFile file) {
		
		location = UPLOADED_FOLDER+location+"/";
		 byte[] bytes;
		try {
			File locate = new File(location);
			locate.mkdir();
			bytes = file.getBytes();
			Path path = Paths.get(location + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
			
		}
		return true;
	}

}

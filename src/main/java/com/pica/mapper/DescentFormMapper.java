package com.pica.mapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.pica.model.Applicant;
import com.pica.model.ApplicantDocument;
import com.pica.model.Dependents;
import com.pica.model.DescentForm;
import com.pica.model.Profile;

public class DescentFormMapper {

//	Dependents dependents = null;

	private static final Logger LOG = LoggerFactory.getLogger(DescentFormMapper.class);
	// private static String  UPLOADED_FOLDER = "C:\\Users\\Younus\\Kastech work space\\jamaican-citizenship-ui\\Kastech workspace\\jamaican-citizenship-ui\\src\\assets\\documents\\";
	private static String UPLOADED_FOLDER = "/Users/MuhammedYounusAttari/git/jamaican-citizenship-ui/Kastech workspace/jamaican-citizenship-ui/src/assets/documents/";
	
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
		profile.setAddress1(descentFormHander.getAddress1());
		profile.setAddress2(descentFormHander.getAddress2());
		profile.setZip(descentFormHander.getZip());
		profile.setCountry(descentFormHander.getCountry());

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
		dependents.setFirstName(descentFormHander.getMfirstname1());
		dependents.setLastName(descentFormHander.getMlastname1());
		dependents.setCob(descentFormHander.getMpob1());
		dependents.setDob(descentFormHander.getMdob1());
		return dependents;
	}

	private static Dependents getMotherDetails(DescentFormHandler descentFormHander) {
		Dependents dependents = new Dependents();
		dependents.setFirstName(descentFormHander.getMfirstname());
		dependents.setLastName(descentFormHander.getMlastname());
		dependents.setPob(descentFormHander.getMpob());
		dependents.setCob(descentFormHander.getMcountry());
		dependents.setDob(descentFormHander.getMdob());
		return dependents;
	}

	private static Dependents getFatherDetails(DescentFormHandler descentFormHander) {

		Dependents dependents = new Dependents();
		dependents.setFirstName(descentFormHander.getFfirstname());
		dependents.setLastName(descentFormHander.getFlastname());
		dependents.setCob(descentFormHander.getFcountry());
		dependents.setDob(descentFormHander.getFdob());
		dependents.setPob(descentFormHander.getFpob());
		return dependents;

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

	public static DescentForm syncDescentForm(DescentForm descentForm, DescentForm descentFormDb) {
		descentFormDb = updateProfileObject(descentForm, descentFormDb);
		return updateDescentFormObject(descentForm, descentFormDb);
	}

	private static DescentForm updateDescentFormObject(DescentForm descentForm, DescentForm descentFormDb) {

		descentFormDb.setFather(descentForm.getFather());
		descentFormDb.setMother(descentForm.getMother());
		descentFormDb.setPaternalFather(descentForm.getPaternalFather());
		descentFormDb.setPaternalMother(descentForm.getPaternalMother());
		descentFormDb.setMaternalFather(descentForm.getMaternalFather());
		descentFormDb.setMaternalMother(descentForm.getMaternalMother());
		return descentFormDb;
	}

	private static DescentForm updateProfileObject(DescentForm descentForm, DescentForm descentFormDb) {

		Profile profile = descentForm.getProfile();

		descentFormDb.getProfile().setAddress1(profile.getAddress1());
		descentFormDb.getProfile().setAddress2(profile.getAddress2());
		descentFormDb.getProfile().setZip(profile.getZip());
		descentFormDb.getProfile().setCountry(profile.getCountry());
		descentFormDb.getProfile().setFirstname(profile.getFirstname());
		descentFormDb.getProfile().setLastname(profile.getLastname());
		descentFormDb.getProfile().setGender(profile.getGender());
		descentFormDb.getProfile().setDob(profile.getDob());
		descentFormDb.getProfile().setNumber(profile.getNumber());
		descentFormDb.getProfile().setPob(profile.getPob());
		// descentFormDb.getProfile().setPassword(profile.getPassword());

		return descentFormDb;

	}

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

	// Merge two list without duplicate.
	@SuppressWarnings({ "unlikely-arg-type", "unused" })
	public static List<Applicant> mergeTwoList(List<Applicant> dbApplicant, List<Applicant> appCodeList) {
		if (dbApplicant != null && appCodeList != null) {
			dbApplicant.remove(appCodeList);
			appCodeList.addAll(dbApplicant);
		}

		return appCodeList;
	}

}

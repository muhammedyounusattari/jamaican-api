package com.pica.mapper;

import com.pica.model.Dependents;
import com.pica.model.DescentForm;
import com.pica.model.Profile;
import com.pica.model.natural.alien.NaturalizationAliens;

public class NaturalizationAliensMapper {

	public static NaturalizationAliens formatPayload(NaturalizationAliensHandler alienFormHander) {

		NaturalizationAliens alienForm = new NaturalizationAliens();
//		descentForm.setId(id);

		Profile profile = getUserProfile(alienFormHander);
		NaturalizationAliens.Section1 section1 = getSection1Details(alienFormHander);
		NaturalizationAliens.Section2 section2 = getSection2Details(alienFormHander);
		NaturalizationAliens.Section3 section3 = getSection3Details(alienFormHander);
		NaturalizationAliens.Section4 section4 = getSection4Details(alienFormHander);
		NaturalizationAliens.Section5 section5 = getSection5Details(alienFormHander);
		NaturalizationAliens.Section6 section6 = getSection6Details(alienFormHander);
		NaturalizationAliens.Section7 section7 = getSection7Details(alienFormHander);
		NaturalizationAliens.Section8 section8 = getSection8Details(alienFormHander);
		NaturalizationAliens.Section9 section9 = getSection9Details(alienFormHander);
		NaturalizationAliens.Section10 section10 = getSection10Details(alienFormHander);
		

		alienForm.setProfile(profile);
		alienForm.setSection1(section1);
		alienForm.setSection2(section2);
		alienForm.setSection3(section3);
		alienForm.setSection4(section4);
		alienForm.setSection5(section5);
		alienForm.setSection6(section6);
		alienForm.setSection7(section7);
		alienForm.setSection8(section8);
		alienForm.setSection9(section9);
		alienForm.setSection10(section10);

		return alienForm;
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

		return profileDb;
	}
	
	private static Profile getUserProfile(NaturalizationAliensHandler alienFormHander) {
		Profile profile = new Profile();
		profile.setId(alienFormHander.getId());
		profile.setFirstname(alienFormHander.getFirstname());
		profile.setLastname(alienFormHander.getLastname());
		profile.setMiddlename(alienFormHander.getMiddlename());
		profile.setAddress1(alienFormHander.getAddress1());
		profile.setAddress2(alienFormHander.getAddress2());
		profile.setZip(alienFormHander.getZip());
		profile.setCountry(alienFormHander.getCountry());

		profile.setDob(alienFormHander.getDob());
		profile.setNumber(alienFormHander.getNumber());
		profile.setEmail(alienFormHander.getEmail());
		profile.setGender(alienFormHander.getGender());
		profile.setPob(alienFormHander.getPob());

		return profile;
	}

	
	
	
	public static NaturalizationAliens syncNaturalAlienForm(NaturalizationAliens naturalizationAliensDTO,
			NaturalizationAliens naturalizationAliensDb) {

		naturalizationAliensDb = updateProfileObject(naturalizationAliensDTO, naturalizationAliensDb);
		return updateNaturalizationAliensObject(naturalizationAliensDTO, naturalizationAliensDb);
	}

	private static NaturalizationAliens updateNaturalizationAliensObject(NaturalizationAliens naturalizationAliensDTO,
			NaturalizationAliens naturalizationAliensDb) {

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

		return naturalizationAliensDb;
	}

	private static NaturalizationAliens updateProfileObject(NaturalizationAliens naturalizationAliensDTO,
			NaturalizationAliens naturalizationAliensDb) {

		Profile profile = naturalizationAliensDTO.getProfile();

		naturalizationAliensDb.getProfile().setAddress1(profile.getAddress1());
		naturalizationAliensDb.getProfile().setAddress2(profile.getAddress2());
		naturalizationAliensDb.getProfile().setZip(profile.getZip());
		naturalizationAliensDb.getProfile().setCountry(profile.getCountry());
		naturalizationAliensDb.getProfile().setFirstname(profile.getFirstname());
		naturalizationAliensDb.getProfile().setLastname(profile.getLastname());
		naturalizationAliensDb.getProfile().setGender(profile.getGender());
		naturalizationAliensDb.getProfile().setDob(profile.getDob());
		naturalizationAliensDb.getProfile().setNumber(profile.getNumber());
		naturalizationAliensDb.getProfile().setPob(profile.getPob());
		// descentFormDb.getProfile().setPassword(profile.getPassword());

		return naturalizationAliensDb;

	}
	
	
	
	private static NaturalizationAliens.Section1 getSection1Details(NaturalizationAliensHandler naturalAlienFormHander) {
		NaturalizationAliens.Section1 section1 = new NaturalizationAliens.Section1();
		section1.setName(naturalAlienFormHander.getName());
		section1.setPrevName(naturalAlienFormHander.getPrevName());
		section1.setOccupation(naturalAlienFormHander.getOccupation());
		section1.setBusAddress(naturalAlienFormHander.getBusAddress());
		return section1;
	}
	
	
	private static NaturalizationAliens.Section2 getSection2Details(NaturalizationAliensHandler naturalAlienFormHander) {
		NaturalizationAliens.Section2 section2 = new NaturalizationAliens.Section2();
		section2.setNationalityBirth(naturalAlienFormHander.getNationalityBirth());
		section2.setNationalityChanged(naturalAlienFormHander.getNationalityChanged());
		section2.setNationalityPresent(naturalAlienFormHander.getNationalityPresent());
		section2.setStateless(naturalAlienFormHander.getStateless());
		section2.setBritishTerritory(naturalAlienFormHander.getBritishTerritory());
		section2.setStatusAcquired(naturalAlienFormHander.getStatusAcquired());
		section2.setNationalityChanged(naturalAlienFormHander.getStateless());
		return section2;
	}
	
	private static NaturalizationAliens.Section3 getSection3Details(NaturalizationAliensHandler naturalAlienFormHander) {
		NaturalizationAliens.Section3 section3 = new NaturalizationAliens.Section3();

		
		section3.setFatherLName(naturalAlienFormHander.getFatherLName());
		section3.setFatherFName(naturalAlienFormHander.getFatherFName());
		section3.setFatherAddress(naturalAlienFormHander.getFatherAddress());
		section3.setFatherCOB(naturalAlienFormHander.getFatherCOB());
		section3.setMotherLName(naturalAlienFormHander.getMotherLName());
		section3.setMotherFName(naturalAlienFormHander.getMotherFName());
		section3.setMotherAddress(naturalAlienFormHander.getMotherAddress());
		section3.setMotherCOB(naturalAlienFormHander.getMotherCOB());
		return section3;
	}
	
	
	private static NaturalizationAliens.Section4 getSection4Details(NaturalizationAliensHandler naturalAlienFormHander) {
		NaturalizationAliens.Section4 section4 = new NaturalizationAliens.Section4();

		
		
		section4.setMaritalStatus(naturalAlienFormHander.getMaritalStatus());
		section4.setDateOfMarriage(naturalAlienFormHander.getDateOfMarriage());
		section4.setPlaceOfMarriabe(naturalAlienFormHander.getPlaceOfMarriabe());
		section4.setHusbandDate(naturalAlienFormHander.getHusbandDate());
		section4.setHusbandPlaceOfDeath(naturalAlienFormHander.getHusbandPlaceOfDeath());
		section4.setMarriageDissolvedDate(naturalAlienFormHander.getMarriageDissolvedDate());
		section4.setMarriagePlaceOfDecree(naturalAlienFormHander.getMarriagePlaceOfDecree());
		section4.setWifeLName(naturalAlienFormHander.getWifeLName());
		section4.setWifeFName(naturalAlienFormHander.getWifeFName());
		section4.setWifeAddress(naturalAlienFormHander.getWifeAddress());
		section4.setWifeCOB(naturalAlienFormHander.getWifeCOB());
		section4.setHusbandLname(naturalAlienFormHander.getHusbandLname());
		section4.setHusbandFName(naturalAlienFormHander.getHusbandFName());
		section4.setHusbandAddress(naturalAlienFormHander.getHusbandAddress());
		section4.setHusbandCOB(naturalAlienFormHander.getHusbandCOB());
		
		return section4;
	}
	
	
	private static NaturalizationAliens.Section5 getSection5Details(NaturalizationAliensHandler naturalAlienFormHander) {
		NaturalizationAliens.Section5 section5 = new NaturalizationAliens.Section5();
		
		section5.setPostalAddress1(naturalAlienFormHander.getPostalAddress1());
		section5.setFromDate1(naturalAlienFormHander.getFromDate1());
		section5.setToDate1(naturalAlienFormHander.getToDate1());
		section5.setPostalAddress2(naturalAlienFormHander.getPostalAddress2());
		section5.setFromDate2(naturalAlienFormHander.getFromDate2());
		section5.setToDate2(naturalAlienFormHander.getToDate2());
		section5.setPostalAddress3(naturalAlienFormHander.getPostalAddress3());
		section5.setFromDate3(naturalAlienFormHander.getFromDate3());
		section5.setToDate3(naturalAlienFormHander.getToDate3());
		section5.setPostalAddress4(naturalAlienFormHander.getPostalAddress4());
		section5.setFromDate4(naturalAlienFormHander.getFromDate4());
		section5.setToDate4(naturalAlienFormHander.getToDate4());
		
		
		return section5;
	}
	
	
	private static NaturalizationAliens.Section6 getSection6Details(NaturalizationAliensHandler naturalAlienFormHander) {
		NaturalizationAliens.Section6 section6 = new NaturalizationAliens.Section6();
		
		section6.setCountry1Visited(naturalAlienFormHander.getCountry1Visited());
		section6.setCountry1FromDate(naturalAlienFormHander.getCountry1FromDate());
		section6.setCountry1ToDate(naturalAlienFormHander.getCountry1ToDate());
		section6.setCountry2Visited(naturalAlienFormHander.getCountry2Visited());
		section6.setCountry2FromDate(naturalAlienFormHander.getCountry2FromDate());
		section6.setCountry2ToDate(naturalAlienFormHander.getCountry2ToDate());
		section6.setCountry3Visited(naturalAlienFormHander.getCountry3Visited());
		section6.setCountry3FromDate(naturalAlienFormHander.getCountry3FromDate());
		section6.setCountry3ToDate(naturalAlienFormHander.getCountry3ToDate());
		section6.setCountry4Visited(naturalAlienFormHander.getCountry4Visited());
		section6.setCountry4FromDate(naturalAlienFormHander.getCountry4FromDate());
		section6.setCountry4ToDate(naturalAlienFormHander.getCountry4ToDate());
		section6.setCountry5Visited(naturalAlienFormHander.getCountry5Visited());
		section6.setCountry5FromDate(naturalAlienFormHander.getCountry5FromDate());
		section6.setCountry5ToDate(naturalAlienFormHander.getCountry5ToDate());
			
		return section6;
	}
	
	
	private static NaturalizationAliens.Section7 getSection7Details(NaturalizationAliensHandler naturalAlienFormHander) {
		NaturalizationAliens.Section7 section7 = new NaturalizationAliens.Section7();
		section7.setNameOfProceeding1(naturalAlienFormHander.getNameOfProceeding1());
		section7.setDate1(naturalAlienFormHander.getDate1());
		section7.setPlace1(naturalAlienFormHander.getPlace1());
		section7.setResult1(naturalAlienFormHander.getResult1());
		section7.setNameOfProceeding2(naturalAlienFormHander.getNameOfProceeding2());
		section7.setDate2(naturalAlienFormHander.getDate2());
		section7.setPlace2(naturalAlienFormHander.getPlace2());
		section7.setResult2(naturalAlienFormHander.getResult2());
		section7.setNameOfProceeding3(naturalAlienFormHander.getNameOfProceeding3());
		section7.setDate3(naturalAlienFormHander.getDate3());
		section7.setPlace3(naturalAlienFormHander.getPlace3());
		section7.setResult3(naturalAlienFormHander.getResult3());
		section7.setNameOfProceeding4(naturalAlienFormHander.getNameOfProceeding4());
		section7.setDate4(naturalAlienFormHander.getDate4());
		section7.setPlace4(naturalAlienFormHander.getPlace4());
		section7.setResult4(naturalAlienFormHander.getResult4());
		section7.setNameOfProceeding5(naturalAlienFormHander.getNameOfProceeding5());
		section7.setDate5(naturalAlienFormHander.getDate5());
		section7.setPlace5(naturalAlienFormHander.getPlace5());
		section7.setResult5(naturalAlienFormHander.getResult5());
		return section7;
	}
	
	
	private static NaturalizationAliens.Section8 getSection8Details(NaturalizationAliensHandler naturalAlienFormHander) {
		NaturalizationAliens.Section8 section8 = new NaturalizationAliens.Section8();
		
		section8.setDateOfComposition(naturalAlienFormHander.getDateOfComposition());
		section8.setDateOfDischargeFromBankruptcy(naturalAlienFormHander.getDateOfDischargeFromBankruptcy());
		section8.setDateOfBankruptcy(naturalAlienFormHander.getDateOfBankruptcy());
		return section8;
	}
	
	
	private static NaturalizationAliens.Section9 getSection9Details(NaturalizationAliensHandler naturalAlienFormHander) {
		NaturalizationAliens.Section9 section9 = new NaturalizationAliens.Section9();
		
		section9.setDateOfAnyPrevApplication(naturalAlienFormHander.getDateOfAnyPrevApplication());
		return section9;
	}

	
	private static NaturalizationAliens.Section10 getSection10Details(NaturalizationAliensHandler naturalAlienFormHander) {
		NaturalizationAliens.Section10 section10 = new NaturalizationAliens.Section10();
		
		
		
		section10.setFullNameUnder21_1(naturalAlienFormHander.getFullNameUnder21_1());
		section10.setDobUnder21_1(naturalAlienFormHander.getDobUnder21_1());
		section10.setPobUnder21_1(naturalAlienFormHander.getPobUnder21_1());
		section10.setCurrentResidenceUnder21_1(naturalAlienFormHander.getCurrentResidenceUnder21_1());
		section10.setFullNameUnder21_2(naturalAlienFormHander.getFullNameUnder21_2());
		section10.setDobUnder21_2(naturalAlienFormHander.getDobUnder21_2());
		section10.setPobUnder21_2(naturalAlienFormHander.getPobUnder21_2());
		section10.setCurrentResidenceUnder21_2(naturalAlienFormHander.getCurrentResidence2());
		section10.setFullNameUnder21_3(naturalAlienFormHander.getFullNameUnder21_3());
		section10.setDobUnder21_3(naturalAlienFormHander.getDobUnder21_3());
		section10.setPobUnder21_3(naturalAlienFormHander.getPobUnder21_3());
		section10.setCurrentResidenceUnder21_3(naturalAlienFormHander.getCurrentResidence3());
		section10.setFullNameUnder21_4(naturalAlienFormHander.getFullNameUnder21_4());
		section10.setDobUnder21_4(naturalAlienFormHander.getDobUnder21_4());
		section10.setPobUnder21_4(naturalAlienFormHander.getPobUnder21_4());
		section10.setCurrentResidenceUnder21_4(naturalAlienFormHander.getCurrentResidenceUnder21_4());
		section10.setFullNameUnder21_5(naturalAlienFormHander.getFullNameUnder21_5());
		section10.setDobUnder21_5(naturalAlienFormHander.getDobUnder21_5());
		section10.setPobUnder21_5(naturalAlienFormHander.getPobUnder21_5());
		section10.setCurrentResidenceUnder21_5(naturalAlienFormHander.getCurrentResidenceUnder21_5());
		
		section10.setFullNameOther1(naturalAlienFormHander.getFullNameOther1());
		section10.setDobOther1(naturalAlienFormHander.getDobOther1());
		section10.setPobOther1(naturalAlienFormHander.getPobOther1());
		section10.setCurrentResidence1(naturalAlienFormHander.getCurrentResidence1());
		section10.setFullNameOther2(naturalAlienFormHander.getFullNameOther2());
		section10.setDobOther2(naturalAlienFormHander.getDobOther2());
		section10.setPobOther2(naturalAlienFormHander.getPobOther2());
		section10.setCurrentResidence2(naturalAlienFormHander.getCurrentResidence2());
		section10.setFullNameOther3(naturalAlienFormHander.getFullNameOther3());
		section10.setDobOther3(naturalAlienFormHander.getDobOther3());
		section10.setPobOther3(naturalAlienFormHander.getPobOther3());
		section10.setCurrentResidence3(naturalAlienFormHander.getCurrentResidence3());
		section10.setFullNameOther4(naturalAlienFormHander.getFullNameOther4());
		section10.setDobOther4(naturalAlienFormHander.getDobOther4());
		section10.setPobOther4(naturalAlienFormHander.getPobOther4());
		section10.setCurrentResidence4(naturalAlienFormHander.getCurrentResidence4());
		section10.setFullNameOther5(naturalAlienFormHander.getFullNameOther5());
		section10.setDobOther5(naturalAlienFormHander.getDobOther5());
		section10.setPobOther5(naturalAlienFormHander.getPobOther5());
		section10.setCurrentResidence5(naturalAlienFormHander.getCurrentResidence5());
		
		return section10;
	}

	
	
	
	
}

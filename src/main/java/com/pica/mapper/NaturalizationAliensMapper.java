//package com.pica.mapper;
//
//import com.pica.model.Profile;
//import com.pica.model.natural.alien.NaturalizationAliens;
//
//public class NaturalizationAliensMapper {
//
//	public static Profile syncProfileForm(Profile profile, Profile profileDb) {
//
//		profileDb.setAddress1(profile.getAddress1());
//		profileDb.setAddress2(profile.getAddress2());
//		profileDb.setZip(profile.getZip());
//		profileDb.setCountry(profile.getCountry());
//		profileDb.setFirstname(profile.getFirstname());
//		profileDb.setLastname(profile.getLastname());
//		profileDb.setGender(profile.getGender());
//		profileDb.setDob(profile.getDob());
//		profileDb.setNumber(profile.getNumber());
//		profileDb.setPob(profile.getPob());
//
//		return profileDb;
//	}
//
//	public static NaturalizationAliens syncNaturalAlienForm(NaturalizationAliens naturalizationAliensDTO,
//			NaturalizationAliens naturalizationAliensDb) {
//
//		naturalizationAliensDb = updateProfileObject(naturalizationAliensDTO, naturalizationAliensDb);
//		return updateNaturalizationAliensObject(naturalizationAliensDTO, naturalizationAliensDb);
//	}
//
//	private static NaturalizationAliens updateNaturalizationAliensObject(NaturalizationAliens naturalizationAliensDTO,
//			NaturalizationAliens naturalizationAliensDb) {
//
//		naturalizationAliensDb.setSection1(naturalizationAliensDTO.getSection1());
//		naturalizationAliensDb.setSection2(naturalizationAliensDTO.getSection2());
//		naturalizationAliensDb.setSection3(naturalizationAliensDTO.getSection3());
//		naturalizationAliensDb.setSection4(naturalizationAliensDTO.getSection4());
//		naturalizationAliensDb.setSection5(naturalizationAliensDTO.getSection5());
//		naturalizationAliensDb.setSection6(naturalizationAliensDTO.getSection6());
//		naturalizationAliensDb.setSection7(naturalizationAliensDTO.getSection7());
//		naturalizationAliensDb.setSection8(naturalizationAliensDTO.getSection8());
//		naturalizationAliensDb.setSection9(naturalizationAliensDTO.getSection9());
//		naturalizationAliensDb.setSection10(naturalizationAliensDTO.getSection10());
//
//		return naturalizationAliensDb;
//	}
//
//	private static NaturalizationAliens updateProfileObject(NaturalizationAliens naturalizationAliensDTO,
//			NaturalizationAliens naturalizationAliensDb) {
//
//		Profile profile = naturalizationAliensDTO.getProfile();
//
//		naturalizationAliensDb.getProfile().setAddress1(profile.getAddress1());
//		naturalizationAliensDb.getProfile().setAddress2(profile.getAddress2());
//		naturalizationAliensDb.getProfile().setZip(profile.getZip());
//		naturalizationAliensDb.getProfile().setCountry(profile.getCountry());
//		naturalizationAliensDb.getProfile().setFirstname(profile.getFirstname());
//		naturalizationAliensDb.getProfile().setLastname(profile.getLastname());
//		naturalizationAliensDb.getProfile().setGender(profile.getGender());
//		naturalizationAliensDb.getProfile().setDob(profile.getDob());
//		naturalizationAliensDb.getProfile().setNumber(profile.getNumber());
//		naturalizationAliensDb.getProfile().setPob(profile.getPob());
//		// descentFormDb.getProfile().setPassword(profile.getPassword());
//
//		return naturalizationAliensDb;
//
//	}
//}

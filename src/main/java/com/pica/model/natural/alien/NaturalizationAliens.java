package com.pica.model.natural.alien;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pica.dto.NaturalizationAliensDto;
import com.pica.model.ApplicantDocument;
import com.pica.model.Profile;

import lombok.Data;

//@Data
@Document
public class NaturalizationAliens {

	@Override
	public String toString() {
		return "NaturalizationAliens [_id=" + _id + ", profile=" + profile + ", section1=" + section1 + ", section2="
				+ section2 + ", section3=" + section3 + ", section4=" + section4 + ", section5=" + section5
				+ ", section6=" + section6 + ", section7=" + section7 + ", section8=" + section8 + ", section9="
				+ section9 + ", section10=" + section10 + ", accept=" + accept + ", getAccept()=" + getAccept()
				+ ", get_id()=" + get_id() + ", getProfile()=" + getProfile() + ", getSection1()=" + getSection1()
				+ ", getSection2()=" + getSection2() + ", getSection3()=" + getSection3() + ", getSection4()="
				+ getSection4() + ", getSection5()=" + getSection5() + ", getSection6()=" + getSection6()
				+ ", getSection7()=" + getSection7() + ", getSection8()=" + getSection8() + ", getSection9()="
				+ getSection9() + ", getSection10()=" + getSection10() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	@Id
	private int _id;

	private Profile profile;
	private Section1 section1;
	private Section2 section2;
	private Section3 section3;
	private Section4 section4;
	private Section5 section5;
	private Section6 section6;
	private Section7 section7;
	private Section8 section8;
	private Section9 section9;
	private Section10 section10;
	private String accept;
	private String appliedDate;
	private String status;
	
	public String getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private List<ApplicantDocument> documents;

	public List<ApplicantDocument> getDocuments() {
		return documents;
	}

	public void setDocuments(List<ApplicantDocument> documents) {
		this.documents = documents;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Section1 getSection1() {
		return section1;
	}

	public void setSection1(Section1 section1) {
		this.section1 = section1;
	}

	public Section2 getSection2() {
		return section2;
	}

	public void setSection2(Section2 section2) {
		this.section2 = section2;
	}

	public Section3 getSection3() {
		return section3;
	}

	public void setSection3(Section3 section3) {
		this.section3 = section3;
	}

	public Section4 getSection4() {
		return section4;
	}

	public void setSection4(Section4 section4) {
		this.section4 = section4;
	}

	public Section5 getSection5() {
		return section5;
	}

	public void setSection5(Section5 section5) {
		this.section5 = section5;
	}

	public Section6 getSection6() {
		return section6;
	}

	public void setSection6(Section6 section6) {
		this.section6 = section6;
	}

	public Section7 getSection7() {
		return section7;
	}

	public void setSection7(Section7 section7) {
		this.section7 = section7;
	}

	public Section8 getSection8() {
		return section8;
	}

	public void setSection8(Section8 section8) {
		this.section8 = section8;
	}

	public Section9 getSection9() {
		return section9;
	}

	public void setSection9(Section9 section9) {
		this.section9 = section9;
	}

	public Section10 getSection10() {
		return section10;
	}

	public void setSection10(Section10 section10) {
		this.section10 = section10;
	}

	public static NaturalizationAliens builder(NaturalizationAliensDto naturalizationAliensDTO) {

		NaturalizationAliens naturalizationAliens = new NaturalizationAliens();
		naturalizationAliens.setProfile(getProfilePayload(naturalizationAliensDTO));
		naturalizationAliens.setSection1(Section1.getSection1(naturalizationAliensDTO));
		naturalizationAliens.setSection2(Section2.getSection2(naturalizationAliensDTO));
		naturalizationAliens.setSection3(Section3.getSection3(naturalizationAliensDTO));
		naturalizationAliens.setSection4(Section4.getSection4(naturalizationAliensDTO));
		naturalizationAliens.setSection5(Section5.getSection5(naturalizationAliensDTO));
		naturalizationAliens.setSection6(Section6.getSection6(naturalizationAliensDTO));
		naturalizationAliens.setSection7(Section7.getSection7(naturalizationAliensDTO));
		naturalizationAliens.setSection8(Section8.getSection8(naturalizationAliensDTO));
		naturalizationAliens.setSection9(Section9.getSection9(naturalizationAliensDTO));
		naturalizationAliens.setSection10(Section10.getSection10(naturalizationAliensDTO));
		naturalizationAliens.setAccept(naturalizationAliens.getAccept());

		return naturalizationAliens;
	}

	private static Profile getProfilePayload(NaturalizationAliensDto naturalizationAliens) {
		Profile profile = new Profile();
		// profile.setId(naturalizationAliens.getId());
		profile.setFirstname(naturalizationAliens.getFirstname());
		profile.setLastname(naturalizationAliens.getLastname());
		profile.setMiddlename(naturalizationAliens.getMiddlename());
		profile.setAddress1(naturalizationAliens.getAddress());
		profile.setAddress2(naturalizationAliens.getAddress());
		// profile.setZip(naturalizationAliens.getZip());
		profile.setCountry(naturalizationAliens.getCountry());

		profile.setDob(naturalizationAliens.getDob());
		profile.setNumber(naturalizationAliens.getNumber());
		profile.setEmail(naturalizationAliens.getEmail());
		profile.setGender(naturalizationAliens.getGender());
		profile.setPob(naturalizationAliens.getPob());

		return profile;

	}

}

@Data
class Section1 {
	private String name;
	private String otherName;
	private String occupation;
	private String busAddress;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getBusAddress() {
		return busAddress;
	}

	public void setBusAddress(String busAddress) {
		this.busAddress = busAddress;
	}

	public static Section1 getSection1(NaturalizationAliensDto naturalizationAliensDTO) {

		Section1 section1 = new Section1();
		section1.setName(naturalizationAliensDTO.getEmail());
		section1.setOtherName(naturalizationAliensDTO.getOthername());
		section1.setOccupation(naturalizationAliensDTO.getOccupation());
		section1.setBusAddress(naturalizationAliensDTO.getBusinessaddress());
		return section1;
	}
}

@Data
class Section2 {
	private String nationalityBirth;
	private String nationalityChanged;
	private String nationalityPresent;
	private String stateless;
	private String britishTerritory;
	private String statusAcquired;
	private String nationalityStatus;

	public String getNationalityBirth() {
		return nationalityBirth;
	}

	public static Section2 getSection2(NaturalizationAliensDto naturalizationAliensDTO) {
		Section2 section2 = new Section2();
		section2.setNationalityBirth(naturalizationAliensDTO.getNationalityControl());
		section2.setNationalityChanged(naturalizationAliensDTO.getNationalityState());
		section2.setNationalityPresent(naturalizationAliensDTO.getPresentnationalityControl());
		section2.setStateless(naturalizationAliensDTO.getNationalityStateless());
		section2.setBritishTerritory(naturalizationAliensDTO.getBritishterritory());
		section2.setStatusAcquired(naturalizationAliensDTO.getStatus());
		section2.setNationalityStatus(naturalizationAliensDTO.getNationalitystatus());

		return section2;
	}

	public void setNationalityBirth(String nationalityBirth) {
		this.nationalityBirth = nationalityBirth;
	}

	public String getNationalityChanged() {
		return nationalityChanged;
	}

	public void setNationalityChanged(String nationalityChanged) {
		this.nationalityChanged = nationalityChanged;
	}

	public String getNationalityPresent() {
		return nationalityPresent;
	}

	public void setNationalityPresent(String nationalityPresent) {
		this.nationalityPresent = nationalityPresent;
	}

	public String getStateless() {
		return stateless;
	}

	public void setStateless(String stateless) {
		this.stateless = stateless;
	}

	public String getBritishTerritory() {
		return britishTerritory;
	}

	public void setBritishTerritory(String britishTerritory) {
		this.britishTerritory = britishTerritory;
	}

	public String getStatusAcquired() {
		return statusAcquired;
	}

	public void setStatusAcquired(String statusAcquired) {
		this.statusAcquired = statusAcquired;
	}

	public String getNationalityStatus() {
		return nationalityStatus;
	}

	public void setNationalityStatus(String nationalityStatus) {
		this.nationalityStatus = nationalityStatus;
	}

}

@Data
class Section3 {
	private String fatherLName;
	private String fatherFName;
	private String fatherAddress;
	private String fatherCOB;

	private String motherLName;
	private String motherFName;
	private String motherAddress;
	private String motherCOB;

	public String getFatherLName() {
		return fatherLName;
	}

	public static Section3 getSection3(NaturalizationAliensDto naturalizationAliensDTO) {
		Section3 section3 = new Section3();
		section3.setFatherLName(naturalizationAliensDTO.getFatherLastName());
		section3.setFatherFName(naturalizationAliensDTO.getFatherFirstName());
		section3.setFatherAddress(naturalizationAliensDTO.getFatherAddress());
		section3.setFatherCOB(naturalizationAliensDTO.getFatherCOB());
		section3.setMotherLName(naturalizationAliensDTO.getMotherLastName());
		section3.setMotherFName(naturalizationAliensDTO.getMotherFirstName());
		section3.setMotherAddress(naturalizationAliensDTO.getMotherAddress());
		section3.setMotherCOB(naturalizationAliensDTO.getMotherCOB());
		return section3;
	}

	public void setFatherLName(String fatherLName) {
		this.fatherLName = fatherLName;
	}

	public String getFatherFName() {
		return fatherFName;
	}

	public void setFatherFName(String fatherFName) {
		this.fatherFName = fatherFName;
	}

	public String getFatherAddress() {
		return fatherAddress;
	}

	public void setFatherAddress(String fatherAddress) {
		this.fatherAddress = fatherAddress;
	}

	public String getFatherCOB() {
		return fatherCOB;
	}

	public void setFatherCOB(String fatherCOB) {
		this.fatherCOB = fatherCOB;
	}

	public String getMotherLName() {
		return motherLName;
	}

	public void setMotherLName(String motherLName) {
		this.motherLName = motherLName;
	}

	public String getMotherFName() {
		return motherFName;
	}

	public void setMotherFName(String motherFName) {
		this.motherFName = motherFName;
	}

	public String getMotherAddress() {
		return motherAddress;
	}

	public void setMotherAddress(String motherAddress) {
		this.motherAddress = motherAddress;
	}

	public String getMotherCOB() {
		return motherCOB;
	}

	public void setMotherCOB(String motherCOB) {
		this.motherCOB = motherCOB;
	}

}

@Data
class Section4 {
	private String maritalStatus;
	private String dateOfMarriage;
	private String placeOfMarriabe;
	private String husbandDate;
	private String husbandPlaceOfDeath;
	private String marriageDissolvedDate;
	private String marriagePlaceOfDecree;

	private String wifeLName;
	private String wifeFName;
	private String wifeAddress;
	private String wifeCOB;

	private String husbandLname;
	private String husbandFName;
	private String husbandAddress;
	private String husbandCOB;

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public static Section4 getSection4(NaturalizationAliensDto naturalizationAliensDTO) {
		Section4 section4 = new Section4();
		section4.setMaritalStatus(naturalizationAliensDTO.getMaritalStatus());
		section4.setDateOfMarriage(naturalizationAliensDTO.getDom());
		section4.setPlaceOfMarriabe(naturalizationAliensDTO.getPom());
		section4.setHusbandDate(naturalizationAliensDTO.getPob());
		section4.setHusbandPlaceOfDeath(naturalizationAliensDTO.getPod());
		section4.setMarriageDissolvedDate(naturalizationAliensDTO.getDodissolved());
		section4.setMarriagePlaceOfDecree(naturalizationAliensDTO.getPodissolved());
		section4.setWifeLName(naturalizationAliensDTO.getWifeLastname());
		section4.setWifeFName(naturalizationAliensDTO.getWifeFirstname());
		section4.setWifeAddress(naturalizationAliensDTO.getWifeAddress());
		section4.setWifeCOB(naturalizationAliensDTO.getWifeCOB());
		section4.setHusbandLname(naturalizationAliensDTO.getHusbandLastname());
		section4.setHusbandFName(naturalizationAliensDTO.getHusbandFirstname());
		section4.setHusbandAddress(naturalizationAliensDTO.getHusbandaddress());
		section4.setHusbandCOB(naturalizationAliensDTO.getHusbandCOB());

		return section4;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getDateOfMarriage() {
		return dateOfMarriage;
	}

	public void setDateOfMarriage(String dateOfMarriage) {
		this.dateOfMarriage = dateOfMarriage;
	}

	public String getPlaceOfMarriabe() {
		return placeOfMarriabe;
	}

	public void setPlaceOfMarriabe(String placeOfMarriabe) {
		this.placeOfMarriabe = placeOfMarriabe;
	}

	public String getHusbandDate() {
		return husbandDate;
	}

	public void setHusbandDate(String husbandDate) {
		this.husbandDate = husbandDate;
	}

	public String getHusbandPlaceOfDeath() {
		return husbandPlaceOfDeath;
	}

	public void setHusbandPlaceOfDeath(String husbandPlaceOfDeath) {
		this.husbandPlaceOfDeath = husbandPlaceOfDeath;
	}

	public String getMarriageDissolvedDate() {
		return marriageDissolvedDate;
	}

	public void setMarriageDissolvedDate(String marriageDissolvedDate) {
		this.marriageDissolvedDate = marriageDissolvedDate;
	}

	public String getMarriagePlaceOfDecree() {
		return marriagePlaceOfDecree;
	}

	public void setMarriagePlaceOfDecree(String marriagePlaceOfDecree) {
		this.marriagePlaceOfDecree = marriagePlaceOfDecree;
	}

	public String getWifeLName() {
		return wifeLName;
	}

	public void setWifeLName(String wifeLName) {
		this.wifeLName = wifeLName;
	}

	public String getWifeFName() {
		return wifeFName;
	}

	public void setWifeFName(String wifeFName) {
		this.wifeFName = wifeFName;
	}

	public String getWifeAddress() {
		return wifeAddress;
	}

	public void setWifeAddress(String wifeAddress) {
		this.wifeAddress = wifeAddress;
	}

	public String getWifeCOB() {
		return wifeCOB;
	}

	public void setWifeCOB(String wifeCOB) {
		this.wifeCOB = wifeCOB;
	}

	public String getHusbandLname() {
		return husbandLname;
	}

	public void setHusbandLname(String husbandLname) {
		this.husbandLname = husbandLname;
	}

	public String getHusbandFName() {
		return husbandFName;
	}

	public void setHusbandFName(String husbandFName) {
		this.husbandFName = husbandFName;
	}

	public String getHusbandAddress() {
		return husbandAddress;
	}

	public void setHusbandAddress(String husbandAddress) {
		this.husbandAddress = husbandAddress;
	}

	public String getHusbandCOB() {
		return husbandCOB;
	}

	public void setHusbandCOB(String husbandCOB) {
		this.husbandCOB = husbandCOB;
	}

}

@Data
class Section5 {
	private String postalAddress1;
	private String fromDate1;
	private String toDate1;

	private String postalAddress2;
	private String fromDate2;
	private String toDate2;

	private String postalAddress3;
	private String fromDate3;
	private String toDate3;

	private String postalAddress4;
	private String fromDate4;
	private String toDate4;

	private String yearsOfResidence;
	
	
	public String getYearsOfResidence() {
		return yearsOfResidence;
	}

	public void setYearsOfResidence(String yearsOfResidence) {
		this.yearsOfResidence = yearsOfResidence;
	}

	public String getPostalAddress1() {
		return postalAddress1;
	}

	public static Section5 getSection5(NaturalizationAliensDto naturalizationAliensDTO) {
		Section5 section5 = new Section5();
		section5.setPostalAddress1(naturalizationAliensDTO.getResidentaddress1());
		section5.setFromDate1(naturalizationAliensDTO.getResidentfrom1());
		section5.setToDate1(naturalizationAliensDTO.getResidentto1());
		section5.setPostalAddress2(naturalizationAliensDTO.getResidentaddress2());
		section5.setFromDate2(naturalizationAliensDTO.getResidentfrom2());
		section5.setToDate2(naturalizationAliensDTO.getResidentto3());
		section5.setPostalAddress3(naturalizationAliensDTO.getResidentaddress3());
		section5.setFromDate3(naturalizationAliensDTO.getResidentfrom3());
		section5.setToDate3(naturalizationAliensDTO.getResidentto3());
		section5.setYearsOfResidence(naturalizationAliensDTO.getYears_block());

		return section5;
	}

	public void setPostalAddress1(String postalAddress1) {
		this.postalAddress1 = postalAddress1;
	}

	public String getFromDate1() {
		return fromDate1;
	}

	public void setFromDate1(String fromDate1) {
		this.fromDate1 = fromDate1;
	}

	public String getToDate1() {
		return toDate1;
	}

	public void setToDate1(String toDate1) {
		this.toDate1 = toDate1;
	}

	public String getPostalAddress2() {
		return postalAddress2;
	}

	public void setPostalAddress2(String postalAddress2) {
		this.postalAddress2 = postalAddress2;
	}

	public String getFromDate2() {
		return fromDate2;
	}

	public void setFromDate2(String fromDate2) {
		this.fromDate2 = fromDate2;
	}

	public String getToDate2() {
		return toDate2;
	}

	public void setToDate2(String toDate2) {
		this.toDate2 = toDate2;
	}

	public String getPostalAddress3() {
		return postalAddress3;
	}

	public void setPostalAddress3(String postalAddress3) {
		this.postalAddress3 = postalAddress3;
	}

	public String getFromDate3() {
		return fromDate3;
	}

	public void setFromDate3(String fromDate3) {
		this.fromDate3 = fromDate3;
	}

	public String getToDate3() {
		return toDate3;
	}

	public void setToDate3(String toDate3) {
		this.toDate3 = toDate3;
	}

	public String getPostalAddress4() {
		return postalAddress4;
	}

	public void setPostalAddress4(String postalAddress4) {
		this.postalAddress4 = postalAddress4;
	}

	public String getFromDate4() {
		return fromDate4;
	}

	public void setFromDate4(String fromDate4) {
		this.fromDate4 = fromDate4;
	}

	public String getToDate4() {
		return toDate4;
	}

	public void setToDate4(String toDate4) {
		this.toDate4 = toDate4;
	}

}

@Data
class Section6 {

	private String country1Visited;
	private String country1FromDate;
	private String country1ToDate;

	private String country2Visited;
	private String country2FromDate;
	private String country2ToDate;

	private String country3Visited;
	private String country3FromDate;
	private String country3ToDate;

	private String country4Visited;
	private String country4FromDate;
	private String country4ToDate;

	private String country5Visited;
	private String country5FromDate;
	private String country5ToDate;

	public String getCountry1Visited() {
		return country1Visited;
	}

	public static Section6 getSection6(NaturalizationAliensDto naturalizationAliensDTO) {
		Section6 section6 = new Section6();
		section6.setCountry1Visited(naturalizationAliensDTO.getVisitedCountries1());
		section6.setCountry1FromDate(naturalizationAliensDTO.getVisitedCountriesfrom1());
		section6.setCountry1ToDate(naturalizationAliensDTO.getVisitedCountriesto1());
		
		section6.setCountry2Visited(naturalizationAliensDTO.getVisitedCountries2());
		section6.setCountry2FromDate(naturalizationAliensDTO.getVisitedCountriesfrom2());
		section6.setCountry2ToDate(naturalizationAliensDTO.getVisitedCountriesto2());
		
		section6.setCountry3Visited(naturalizationAliensDTO.getVisitedCountries3());
		section6.setCountry3FromDate(naturalizationAliensDTO.getVisitedCountriesfrom3());
		section6.setCountry3ToDate(naturalizationAliensDTO.getVisitedCountriesto3());
		
		section6.setCountry4Visited(naturalizationAliensDTO.getVisitedCountries4());
		section6.setCountry4FromDate(naturalizationAliensDTO.getVisitedCountriesfrom4());
		section6.setCountry4ToDate(naturalizationAliensDTO.getVisitedCountriesto4());
		
		section6.setCountry5Visited(naturalizationAliensDTO.getVisitedCountries5());
		section6.setCountry5FromDate(naturalizationAliensDTO.getVisitedCountriesfrom5());
		section6.setCountry5ToDate(naturalizationAliensDTO.getVisitedCountriesto5());
		
		
		
		return section6;
	}

	public void setCountry1Visited(String country1Visited) {
		this.country1Visited = country1Visited;
	}

	public String getCountry1FromDate() {
		return country1FromDate;
	}

	public void setCountry1FromDate(String country1FromDate) {
		this.country1FromDate = country1FromDate;
	}

	public String getCountry1ToDate() {
		return country1ToDate;
	}

	public void setCountry1ToDate(String country1ToDate) {
		this.country1ToDate = country1ToDate;
	}

	public String getCountry2Visited() {
		return country2Visited;
	}

	public void setCountry2Visited(String country2Visited) {
		this.country2Visited = country2Visited;
	}

	public String getCountry2FromDate() {
		return country2FromDate;
	}

	public void setCountry2FromDate(String country2FromDate) {
		this.country2FromDate = country2FromDate;
	}

	public String getCountry2ToDate() {
		return country2ToDate;
	}

	public void setCountry2ToDate(String country2ToDate) {
		this.country2ToDate = country2ToDate;
	}

	public String getCountry3Visited() {
		return country3Visited;
	}

	public void setCountry3Visited(String country3Visited) {
		this.country3Visited = country3Visited;
	}

	public String getCountry3FromDate() {
		return country3FromDate;
	}

	public void setCountry3FromDate(String country3FromDate) {
		this.country3FromDate = country3FromDate;
	}

	public String getCountry3ToDate() {
		return country3ToDate;
	}

	public void setCountry3ToDate(String country3ToDate) {
		this.country3ToDate = country3ToDate;
	}

	public String getCountry4Visited() {
		return country4Visited;
	}

	public void setCountry4Visited(String country4Visited) {
		this.country4Visited = country4Visited;
	}

	public String getCountry4FromDate() {
		return country4FromDate;
	}

	public void setCountry4FromDate(String country4FromDate) {
		this.country4FromDate = country4FromDate;
	}

	public String getCountry4ToDate() {
		return country4ToDate;
	}

	public void setCountry4ToDate(String country4ToDate) {
		this.country4ToDate = country4ToDate;
	}

	public String getCountry5Visited() {
		return country5Visited;
	}

	public void setCountry5Visited(String country5Visited) {
		this.country5Visited = country5Visited;
	}

	public String getCountry5FromDate() {
		return country5FromDate;
	}

	public void setCountry5FromDate(String country5FromDate) {
		this.country5FromDate = country5FromDate;
	}

	public String getCountry5ToDate() {
		return country5ToDate;
	}

	public void setCountry5ToDate(String country5ToDate) {
		this.country5ToDate = country5ToDate;
	}

}

@Data
class Section7 {

	private String nameOfProceeding1;
	private String date1;
	private String place1;
	private String result1;

	private String nameOfProceeding2;
	private String date2;
	private String place2;
	private String result2;

	private String nameOfProceeding3;
	private String date3;
	private String place3;
	private String result3;

	private String nameOfProceeding4;
	private String date4;
	private String place4;
	private String result4;

	private String nameOfProceeding5;
	private String date5;
	private String place5;
	private String result5;

	public String getNameOfProceeding1() {
		return nameOfProceeding1;
	}

	public static Section7 getSection7(NaturalizationAliensDto naturalizationAliensDTO) {
		Section7 section7 = new Section7();
		section7.setNameOfProceeding1(naturalizationAliensDTO.getProceedingsname1());
		section7.setDate1(naturalizationAliensDTO.getProceedingsDate1());
		section7.setPlace1(naturalizationAliensDTO.getProceedingsPlace1());
		section7.setResult1(naturalizationAliensDTO.getProceedingsResult1());
		
		section7.setNameOfProceeding2(naturalizationAliensDTO.getProceedingsname2());
		section7.setDate2(naturalizationAliensDTO.getProceedingsDate2());
		section7.setPlace2(naturalizationAliensDTO.getProceedingsPlace2());
		section7.setResult2(naturalizationAliensDTO.getProceedingsResult2());
		
		section7.setNameOfProceeding3(naturalizationAliensDTO.getProceedingsname3());
		section7.setDate3(naturalizationAliensDTO.getProceedingsDate3());
		section7.setPlace3(naturalizationAliensDTO.getProceedingsPlace3());
		section7.setResult3(naturalizationAliensDTO.getProceedingsResult3());
		
		section7.setNameOfProceeding4(naturalizationAliensDTO.getProceedingsname4());
		section7.setDate4(naturalizationAliensDTO.getProceedingsDate4());
		section7.setPlace4(naturalizationAliensDTO.getProceedingsPlace4());
		section7.setResult4(naturalizationAliensDTO.getProceedingsResult4());
		
		section7.setNameOfProceeding5(naturalizationAliensDTO.getProceedingsname5());
		section7.setDate5(naturalizationAliensDTO.getProceedingsDate5());
		section7.setPlace5(naturalizationAliensDTO.getProceedingsPlace5());
		section7.setResult5(naturalizationAliensDTO.getProceedingsResult5());
		
		return section7;
	}

	public void setNameOfProceeding1(String nameOfProceeding1) {
		this.nameOfProceeding1 = nameOfProceeding1;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getPlace1() {
		return place1;
	}

	public void setPlace1(String place1) {
		this.place1 = place1;
	}

	public String getResult1() {
		return result1;
	}

	public void setResult1(String result1) {
		this.result1 = result1;
	}

	public String getNameOfProceeding2() {
		return nameOfProceeding2;
	}

	public void setNameOfProceeding2(String nameOfProceeding2) {
		this.nameOfProceeding2 = nameOfProceeding2;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getPlace2() {
		return place2;
	}

	public void setPlace2(String place2) {
		this.place2 = place2;
	}

	public String getResult2() {
		return result2;
	}

	public void setResult2(String result2) {
		this.result2 = result2;
	}

	public String getNameOfProceeding3() {
		return nameOfProceeding3;
	}

	public void setNameOfProceeding3(String nameOfProceeding3) {
		this.nameOfProceeding3 = nameOfProceeding3;
	}

	public String getDate3() {
		return date3;
	}

	public void setDate3(String date3) {
		this.date3 = date3;
	}

	public String getPlace3() {
		return place3;
	}

	public void setPlace3(String place3) {
		this.place3 = place3;
	}

	public String getResult3() {
		return result3;
	}

	public void setResult3(String result3) {
		this.result3 = result3;
	}

	public String getNameOfProceeding4() {
		return nameOfProceeding4;
	}

	public void setNameOfProceeding4(String nameOfProceeding4) {
		this.nameOfProceeding4 = nameOfProceeding4;
	}

	public String getDate4() {
		return date4;
	}

	public void setDate4(String date4) {
		this.date4 = date4;
	}

	public String getPlace4() {
		return place4;
	}

	public void setPlace4(String place4) {
		this.place4 = place4;
	}

	public String getResult4() {
		return result4;
	}

	public void setResult4(String result4) {
		this.result4 = result4;
	}

	public String getNameOfProceeding5() {
		return nameOfProceeding5;
	}

	public void setNameOfProceeding5(String nameOfProceeding5) {
		this.nameOfProceeding5 = nameOfProceeding5;
	}

	public String getDate5() {
		return date5;
	}

	public void setDate5(String date5) {
		this.date5 = date5;
	}

	public String getPlace5() {
		return place5;
	}

	public void setPlace5(String place5) {
		this.place5 = place5;
	}

	public String getResult5() {
		return result5;
	}

	public void setResult5(String result5) {
		this.result5 = result5;
	}

}

@Data
class Section8 {
	private String dateOfComposition;
	private String dateOfDischargeFromBankruptcy;
	private String dateOfBankruptcy;

	public static Section8 getSection8(NaturalizationAliensDto naturalizationAliensDTO) {
		Section8 section8 = new Section8();
		section8.setDateOfComposition(naturalizationAliensDTO.getProceedingsname1());//todo -need fille with exact value
		section8.setDateOfBankruptcy(naturalizationAliensDTO.getBankruptcyDate());
		section8.setDateOfDischargeFromBankruptcy(naturalizationAliensDTO.getDischargeBankruptcy());
		return section8;
	}

	public String getDateOfComposition() {
		return dateOfComposition;
	}

	public void setDateOfComposition(String dateOfComposition) {
		this.dateOfComposition = dateOfComposition;
	}

	public String getDateOfDischargeFromBankruptcy() {
		return dateOfDischargeFromBankruptcy;
	}

	public void setDateOfDischargeFromBankruptcy(String dateOfDischargeFromBankruptcy) {
		this.dateOfDischargeFromBankruptcy = dateOfDischargeFromBankruptcy;
	}

	public String getDateOfBankruptcy() {
		return dateOfBankruptcy;
	}

	public void setDateOfBankruptcy(String dateOfBankruptcy) {
		this.dateOfBankruptcy = dateOfBankruptcy;
	}
	
	
}

@Data
class Section9 {
	private String dateOfAnyPrevApplication;

	public String getDateOfAnyPrevApplication() {
		return dateOfAnyPrevApplication;
	}

	public static Section9 getSection9(NaturalizationAliensDto naturalizationAliensDTO) {
		Section9 section9 = new Section9();
		section9.setDateOfAnyPrevApplication(naturalizationAliensDTO.getPreviousAppDate());
		return section9;
	}

	public void setDateOfAnyPrevApplication(String dateOfAnyPrevApplication) {
		this.dateOfAnyPrevApplication = dateOfAnyPrevApplication;
	}

}

@Data
class Section10 {
	private String fullNameUnder21_1;
	private String dobUnder21_1;
	private String pobUnder21_1;
	private String currentResidenceUnder21_1;

	private String fullNameUnder21_2;
	private String dobUnder21_2;
	private String pobUnder21_2;
	private String currentResidenceUnder21_2;


	private String fullNameOther1;
	private String dobOther1;
	private String pobOther1;
	private String currentResidence1;

	private String fullNameOther2;
	private String dobOther2;
	private String pobOther2;
	private String currentResidence2;

	

	public String getFullNameUnder21_1() {
		return fullNameUnder21_1;
	}

	public static Section10 getSection10(NaturalizationAliensDto naturalizationAliensDTO) {
		
		Section10 section10 =new Section10();
		section10.setFullNameUnder21_1(naturalizationAliensDTO.getChildrenName1());
		section10.setDobUnder21_1(naturalizationAliensDTO.getChildrenDob1());
		section10.setPobUnder21_1(naturalizationAliensDTO.getChildrenPob1());
		section10.setCurrentResidence1(naturalizationAliensDTO.getChildrenresidence1());
		section10.setFullNameUnder21_2(naturalizationAliensDTO.getChildrenName2());
		section10.setDobUnder21_2(naturalizationAliensDTO.getChildrenDob2());
		section10.setPobUnder21_2(naturalizationAliensDTO.getChildrenPob2());
		section10.setCurrentResidence2(naturalizationAliensDTO.getChildrenresidence2());
		
		section10.setFullNameOther1(naturalizationAliensDTO.getChildrenNameother1());
		section10.setDobOther1(naturalizationAliensDTO.getChildrenDobother1());
		section10.setPobOther1(naturalizationAliensDTO.getChildrenPobother1());
		section10.setCurrentResidence1(naturalizationAliensDTO.getChildrenresidenceother1());
		section10.setFullNameOther2(naturalizationAliensDTO.getChildrenNameother2());
		section10.setDobOther2(naturalizationAliensDTO.getChildrenDobother2());
		section10.setPobOther2(naturalizationAliensDTO.getChildrenPobother2());
		section10.setCurrentResidence2(naturalizationAliensDTO.getChildrenresidenceother2());

		
		return section10;
	}

	public void setFullNameUnder21_1(String fullNameUnder21_1) {
		this.fullNameUnder21_1 = fullNameUnder21_1;
	}

	public String getDobUnder21_1() {
		return dobUnder21_1;
	}

	public void setDobUnder21_1(String dobUnder21_1) {
		this.dobUnder21_1 = dobUnder21_1;
	}

	public String getPobUnder21_1() {
		return pobUnder21_1;
	}

	public void setPobUnder21_1(String pobUnder21_1) {
		this.pobUnder21_1 = pobUnder21_1;
	}

	public String getCurrentResidenceUnder21_1() {
		return currentResidenceUnder21_1;
	}

	public void setCurrentResidenceUnder21_1(String currentResidenceUnder21_1) {
		this.currentResidenceUnder21_1 = currentResidenceUnder21_1;
	}

	public String getFullNameUnder21_2() {
		return fullNameUnder21_2;
	}

	public void setFullNameUnder21_2(String fullNameUnder21_2) {
		this.fullNameUnder21_2 = fullNameUnder21_2;
	}

	public String getDobUnder21_2() {
		return dobUnder21_2;
	}

	public void setDobUnder21_2(String dobUnder21_2) {
		this.dobUnder21_2 = dobUnder21_2;
	}

	public String getPobUnder21_2() {
		return pobUnder21_2;
	}

	public void setPobUnder21_2(String pobUnder21_2) {
		this.pobUnder21_2 = pobUnder21_2;
	}

	public String getCurrentResidenceUnder21_2() {
		return currentResidenceUnder21_2;
	}

	public void setCurrentResidenceUnder21_2(String currentResidenceUnder21_2) {
		this.currentResidenceUnder21_2 = currentResidenceUnder21_2;
	}


	public String getFullNameOther1() {
		return fullNameOther1;
	}

	public void setFullNameOther1(String fullNameOther1) {
		this.fullNameOther1 = fullNameOther1;
	}

	public String getDobOther1() {
		return dobOther1;
	}

	public void setDobOther1(String dobOther1) {
		this.dobOther1 = dobOther1;
	}

	public String getPobOther1() {
		return pobOther1;
	}

	public void setPobOther1(String pobOther1) {
		this.pobOther1 = pobOther1;
	}

	public String getCurrentResidence1() {
		return currentResidence1;
	}

	public void setCurrentResidence1(String currentResidence1) {
		this.currentResidence1 = currentResidence1;
	}

	public String getFullNameOther2() {
		return fullNameOther2;
	}

	public void setFullNameOther2(String fullNameOther2) {
		this.fullNameOther2 = fullNameOther2;
	}

	public String getDobOther2() {
		return dobOther2;
	}

	public void setDobOther2(String dobOther2) {
		this.dobOther2 = dobOther2;
	}

	public String getPobOther2() {
		return pobOther2;
	}

	public void setPobOther2(String pobOther2) {
		this.pobOther2 = pobOther2;
	}

	public String getCurrentResidence2() {
		return currentResidence2;
	}

	public void setCurrentResidence2(String currentResidence2) {
		this.currentResidence2 = currentResidence2;
	}

	

}

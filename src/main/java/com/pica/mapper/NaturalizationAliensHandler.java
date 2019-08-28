package com.pica.mapper;

import org.springframework.stereotype.Component;

@Component
public class NaturalizationAliensHandler {

	//profile attributes

//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	
//	@NotNull(message = "lastname cannot be null")
	private String lastname;
	
	private String middlename;
	
//	@NotNull(message = "firstname cannot be null")
	private String firstname;
	
//	@NotNull(message="date of birth cannot be null")
	private String dob;
	
//	@NotNull(message = "place of birth cannot be null")
	private String pob;
	
//	@NotNull(message = "email cannot be null")
	private String email;
	
//	@NotNull(message = "address cannot be null")
	private String address1;
	
	private String address2;
	
	private String country;
	
	private String zip;
	
//	@NotNull(message = "number cannot be null")
	private String number;
	
//	@NotNull(message = "gender cannot be null")
	private String gender;
	
//	@NotNull(message = "password cannot be null")
	private String password;
	
	
	
	
	
	private String name;
	private String prevName;
	private String occupation;
	private String busAddress;
	private String nationalityBirth;
	private String nationalityChanged;
	private String nationalityPresent;
	private String stateless;
	private String britishTerritory;
	private String statusAcquired;
	private String nationalityStatus;
	private String fatherLName;
	private String fatherFName;
	private String fatherAddress;
	private String fatherCOB;
	
	private String motherLName;
	private String motherFName;
	private String motherAddress;
	private String motherCOB;
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
	private String dateOfComposition;
	private String dateOfDischargeFromBankruptcy;
	private String dateOfBankruptcy;
	
	private String dateOfAnyPrevApplication;

	private String fullNameUnder21_1;
	private String dobUnder21_1;
	private String pobUnder21_1;
	private String currentResidenceUnder21_1;
	
	private String fullNameUnder21_2;
	private String dobUnder21_2;
	private String pobUnder21_2;
	private String currentResidenceUnder21_2;
	
	private String fullNameUnder21_3;
	private String dobUnder21_3;
	private String pobUnder21_3;
	private String currentResidenceUnder21_3;
	
	private String fullNameUnder21_4;
	private String dobUnder21_4;
	private String pobUnder21_4;
	private String currentResidenceUnder21_4;
	
	private String fullNameUnder21_5;
	private String dobUnder21_5;
	private String pobUnder21_5;
	private String currentResidenceUnder21_5;
	
	
	private String fullNameOther1;
	private String dobOther1;
	private String pobOther1;
	private String currentResidence1;
	
	private String fullNameOther2;
	private String dobOther2;
	private String pobOther2;
	private String currentResidence2;
	
	private String fullNameOther3;
	private String dobOther3;
	private String pobOther3;
	private String currentResidence3;
	
	private String fullNameOther4;
	private String dobOther4;
	private String pobOther4;
	private String currentResidence4;
	
	private String fullNameOther5;
	private String dobOther5;
	private String pobOther5;
	private String currentResidence5;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrevName() {
		return prevName;
	}
	public void setPrevName(String prevName) {
		this.prevName = prevName;
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
	public String getNationalityBirth() {
		return nationalityBirth;
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
	public String getFatherLName() {
		return fatherLName;
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
	public String getMaritalStatus() {
		return maritalStatus;
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
	public String getPostalAddress1() {
		return postalAddress1;
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
	public String getCountry1Visited() {
		return country1Visited;
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
	public String getNameOfProceeding1() {
		return nameOfProceeding1;
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
	public String getDateOfAnyPrevApplication() {
		return dateOfAnyPrevApplication;
	}
	public void setDateOfAnyPrevApplication(String dateOfAnyPrevApplication) {
		this.dateOfAnyPrevApplication = dateOfAnyPrevApplication;
	}
	public String getFullNameUnder21_1() {
		return fullNameUnder21_1;
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
	public String getFullNameUnder21_3() {
		return fullNameUnder21_3;
	}
	public void setFullNameUnder21_3(String fullNameUnder21_3) {
		this.fullNameUnder21_3 = fullNameUnder21_3;
	}
	public String getDobUnder21_3() {
		return dobUnder21_3;
	}
	public void setDobUnder21_3(String dobUnder21_3) {
		this.dobUnder21_3 = dobUnder21_3;
	}
	public String getPobUnder21_3() {
		return pobUnder21_3;
	}
	public void setPobUnder21_3(String pobUnder21_3) {
		this.pobUnder21_3 = pobUnder21_3;
	}
	public String getCurrentResidenceUnder21_3() {
		return currentResidenceUnder21_3;
	}
	public void setCurrentResidenceUnder21_3(String currentResidenceUnder21_3) {
		this.currentResidenceUnder21_3 = currentResidenceUnder21_3;
	}
	public String getFullNameUnder21_4() {
		return fullNameUnder21_4;
	}
	public void setFullNameUnder21_4(String fullNameUnder21_4) {
		this.fullNameUnder21_4 = fullNameUnder21_4;
	}
	public String getDobUnder21_4() {
		return dobUnder21_4;
	}
	public void setDobUnder21_4(String dobUnder21_4) {
		this.dobUnder21_4 = dobUnder21_4;
	}
	public String getPobUnder21_4() {
		return pobUnder21_4;
	}
	public void setPobUnder21_4(String pobUnder21_4) {
		this.pobUnder21_4 = pobUnder21_4;
	}
	public String getCurrentResidenceUnder21_4() {
		return currentResidenceUnder21_4;
	}
	public void setCurrentResidenceUnder21_4(String currentResidenceUnder21_4) {
		this.currentResidenceUnder21_4 = currentResidenceUnder21_4;
	}
	public String getFullNameUnder21_5() {
		return fullNameUnder21_5;
	}
	public void setFullNameUnder21_5(String fullNameUnder21_5) {
		this.fullNameUnder21_5 = fullNameUnder21_5;
	}
	public String getDobUnder21_5() {
		return dobUnder21_5;
	}
	public void setDobUnder21_5(String dobUnder21_5) {
		this.dobUnder21_5 = dobUnder21_5;
	}
	public String getPobUnder21_5() {
		return pobUnder21_5;
	}
	public void setPobUnder21_5(String pobUnder21_5) {
		this.pobUnder21_5 = pobUnder21_5;
	}
	public String getCurrentResidenceUnder21_5() {
		return currentResidenceUnder21_5;
	}
	public void setCurrentResidenceUnder21_5(String currentResidenceUnder21_5) {
		this.currentResidenceUnder21_5 = currentResidenceUnder21_5;
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
	public String getFullNameOther3() {
		return fullNameOther3;
	}
	public void setFullNameOther3(String fullNameOther3) {
		this.fullNameOther3 = fullNameOther3;
	}
	public String getDobOther3() {
		return dobOther3;
	}
	public void setDobOther3(String dobOther3) {
		this.dobOther3 = dobOther3;
	}
	public String getPobOther3() {
		return pobOther3;
	}
	public void setPobOther3(String pobOther3) {
		this.pobOther3 = pobOther3;
	}
	public String getCurrentResidence3() {
		return currentResidence3;
	}
	public void setCurrentResidence3(String currentResidence3) {
		this.currentResidence3 = currentResidence3;
	}
	public String getFullNameOther4() {
		return fullNameOther4;
	}
	public void setFullNameOther4(String fullNameOther4) {
		this.fullNameOther4 = fullNameOther4;
	}
	public String getDobOther4() {
		return dobOther4;
	}
	public void setDobOther4(String dobOther4) {
		this.dobOther4 = dobOther4;
	}
	public String getPobOther4() {
		return pobOther4;
	}
	public void setPobOther4(String pobOther4) {
		this.pobOther4 = pobOther4;
	}
	public String getCurrentResidence4() {
		return currentResidence4;
	}
	public void setCurrentResidence4(String currentResidence4) {
		this.currentResidence4 = currentResidence4;
	}
	public String getFullNameOther5() {
		return fullNameOther5;
	}
	public void setFullNameOther5(String fullNameOther5) {
		this.fullNameOther5 = fullNameOther5;
	}
	public String getDobOther5() {
		return dobOther5;
	}
	public void setDobOther5(String dobOther5) {
		this.dobOther5 = dobOther5;
	}
	public String getPobOther5() {
		return pobOther5;
	}
	public void setPobOther5(String pobOther5) {
		this.pobOther5 = pobOther5;
	}
	public String getCurrentResidence5() {
		return currentResidence5;
	}
	public void setCurrentResidence5(String currentResidence5) {
		this.currentResidence5 = currentResidence5;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPob() {
		return pob;
	}
	public void setPob(String pob) {
		this.pob = pob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

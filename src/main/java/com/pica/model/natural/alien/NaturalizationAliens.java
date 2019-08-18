package com.pica.model.natural.alien;

import org.springframework.data.mongodb.core.mapping.Document;

import com.pica.model.Profile;

import lombok.Data;

@Data
@Document
public class NaturalizationAliens {
	
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
	
	
}



@Data
class Section1{
	private String name;
	private String prevName;
	private String occupation;
	private String busAddress;
}

@Data
class Section2{
	private String nationalityBirth;
	private String nationalityChanged;
	private String nationalityPresent;
	private String stateless;
	private String britishTerritory;
	private String statusAcquired;
	private String nationalityStatus;
}

@Data
class Section3{
	private String fatherLName;
	private String fatherFName;
	private String fatherAddress;
	private String fatherCOB;
	
	private String motherLName;
	private String motherFName;
	private String motherAddress;
	private String motherCOB;
	
}

@Data
class Section4{
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
	
}

@Data
class Section5{
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
}

@Data
class Section6{
	
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
	
}

@Data
class Section7{

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
	
}

@Data
class Section8{
	private String dateOfComposition;
	private String dateOfDischargeFromBankruptcy;
	private String dateOfBankruptcy;
}

@Data
class Section9{
	private String dateOfAnyPrevApplication;
}

@Data
class Section10{
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
}


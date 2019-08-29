package com.pica.mapper;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class DescentFormHandler {
	
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
	private String FormStatus;
	private String FormAppCode;
	
	private String country;
	
	private String zip;
	
//	@NotNull(message = "number cannot be null")
	private String number;
	
//	@NotNull(message = "gender cannot be null")
	private String gender;
	
//	@NotNull(message = "password cannot be null")
	private String password;

	private String ffirstname;
	private String flastname;
	private String fdob;
	private String fpob;
	private String mlastname;
	private String mfirstname;
	private String mdob;
	private String mpob;
	private String mcountry;
	private String fcountry;

	private String plastname1;
	private String pfirstname1;
	private String pdob1;
	private String ppob1;
	private String plastname2;
	private String pfirstname2;
	private String pdob2;
	private String ppob2;

	private String mlastname1;
	private String mfirstname1;
	private String mdob1;
	private String mpob1;
	private String mlastname2;
	private String mfirstname2;
	private String mdob2;
	private String mpob2;

	
	
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

	public String getFfirstname() {
		return ffirstname;
	}

	public void setFfirstname(String ffirstname) {
		this.ffirstname = ffirstname;
	}

	public String getFlastname() {
		return flastname;
	}

	public void setFlastname(String flastname) {
		this.flastname = flastname;
	}

	public void setMfirstname(String mfirstname) {
		this.mfirstname = mfirstname;
	}

	public String getFdob() {
		return fdob;
	}

	public void setFdob(String fdob) {
		this.fdob = fdob;
	}

	public String getFpob() {
		return fpob;
	}

	public void setFpob(String fpob) {
		this.fpob = fpob;
	}

	public String getMlastname() {
		return mlastname;
	}

	public void setMlastname(String mlastname) {
		this.mlastname = mlastname;
	}

	public String getMfirstname() {
		return mfirstname;
	}

	public void setMfastname(String mfirstname) {
		this.mfirstname = mfirstname;
	}

	public String getMdob() {
		return mdob;
	}

	public void setMdob(String mdob) {
		this.mdob = mdob;
	}

	public String getMpob() {
		return mpob;
	}

	public void setMpob(String mpob) {
		this.mpob = mpob;
	}

	public String getPlastname1() {
		return plastname1;
	}

	public void setPlastname1(String plastname1) {
		this.plastname1 = plastname1;
	}

	public String getPfirstname1() {
		return pfirstname1;
	}

	public void setPfirstname1(String pfirstname1) {
		this.pfirstname1 = pfirstname1;
	}

	public String getPdob1() {
		return pdob1;
	}

	public void setPdob1(String pdob1) {
		this.pdob1 = pdob1;
	}

	public String getPpob1() {
		return ppob1;
	}

	public void setPpob1(String ppob1) {
		this.ppob1 = ppob1;
	}

	public String getPlastname2() {
		return plastname2;
	}

	public void setPlastname2(String plastname2) {
		this.plastname2 = plastname2;
	}

	public String getPfirstname2() {
		return pfirstname2;
	}

	public void setPfirstname2(String pfirstname2) {
		this.pfirstname2 = pfirstname2;
	}

	public String getPdob2() {
		return pdob2;
	}

	public void setPdob2(String pdob2) {
		this.pdob2 = pdob2;
	}

	public String getPpob2() {
		return ppob2;
	}

	public void setPpob2(String ppob2) {
		this.ppob2 = ppob2;
	}

	public String getMlastname1() {
		return mlastname1;
	}

	public void setMlastname1(String mlastname1) {
		this.mlastname1 = mlastname1;
	}

	public String getMfirstname1() {
		return mfirstname1;
	}

	public void setMfirstname1(String mfirstname1) {
		this.mfirstname1 = mfirstname1;
	}

	public String getMdob1() {
		return mdob1;
	}

	public void setMdob1(String mdob1) {
		this.mdob1 = mdob1;
	}

	public String getMpob1() {
		return mpob1;
	}

	public void setMpob1(String mpob1) {
		this.mpob1 = mpob1;
	}

	public String getMlastname2() {
		return mlastname2;
	}

	public void setMlastname2(String mlastname2) {
		this.mlastname2 = mlastname2;
	}

	public String getMfirstname2() {
		return mfirstname2;
	}

	public void setMfirstname2(String mfirstname2) {
		this.mfirstname2 = mfirstname2;
	}

	public String getMdob2() {
		return mdob2;
	}

	public void setMdob2(String mdob2) {
		this.mdob2 = mdob2;
	}

	public String getMpob2() {
		return mpob2;
	}

	public void setMpob2(String mpob2) {
		this.mpob2 = mpob2;
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

	public void setZipcode(String zip) {
		this.zip = zip;
	}

	public String getMcountry() {
		return mcountry;
	}

	public void setMcountry(String mcountry) {
		this.mcountry = mcountry;
	}

	public String getFcountry() {
		return fcountry;
	}

	public void setFcountry(String fcountry) {
		this.fcountry = fcountry;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getFormStatus() {
		return FormStatus;
	}

	public void setFormStatus(String formStatus) {
		FormStatus = formStatus;
	}

	public String getFormAppCode() {
		return FormAppCode;
	}

	public void setFormAppCode(String formAppCode) {
		FormAppCode = formAppCode;
	}
	

}

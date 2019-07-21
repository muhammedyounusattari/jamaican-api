package com.pica.model;

import javax.validation.constraints.NotNull;

//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pica.commons.PICAApplictions;

@Document(collection="profile")

public class Profile {

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private int _id;
	
	@NotNull(message = "lastname cannot be null")
	private String lastname;
	
	private String middlename;
	
	@NotNull(message = "firstname cannot be null")
	private String firstname;
	
	@NotNull(message="date of birth cannot be null")
	private String dob;
	
	@NotNull(message = "place of birth cannot be null")
	private String pob;
	
	@NotNull(message = "place of birth cannot be null")
	private String country;
	
	@NotNull(message = "email cannot be null")
	private String email;
	
	@NotNull(message = "address cannot be null")
	private String address1;
	
	@NotNull(message = "address cannot be null")
	private String address2;

	@NotNull(message = "Zip code cannot be null")
	private String zip;
	
	@NotNull(message = "number cannot be null")
	private String number;
	
	@NotNull(message = "gender cannot be null")
	private String gender;
	
//	@NotNull(message = "password cannot be null")
	private String password;
	
	private String base29Code;
	
	private String appCode;
	
	private String appliedDate;
	
	private boolean passwordChanged;
	
	private PICAApplictions applied;
	
	private String loginType;
	
	private String comment;
	
	public boolean isPasswordChanged() {
		return passwordChanged;
	}

	public void setPasswordChanged(boolean passwordChanged) {
		this.passwordChanged = passwordChanged;
	}

	public String getBase29Code() {
		return base29Code;
	}

	public void setBase29Code(String base29Code) {
		this.base29Code = base29Code;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	private String custId;
	
	private String appliedFor;
	
	public String getAppliedFor() {
		return appliedFor;
	}

	public void setAppliedFor(String appliedFor) {
		this.appliedFor = appliedFor;
	}

	private String status;
	
	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
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
		this.email = email.toLowerCase();
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

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}

	public String getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public void setApplied(PICAApplictions applied) {
		this.applied = applied;
	}
	
	public PICAApplictions getApplied() {
		return this.applied;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
	}
	
}

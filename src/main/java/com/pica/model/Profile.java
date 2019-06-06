package com.pica.model;

import javax.validation.constraints.NotNull;

//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="profile")
public class Profile {

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull(message = "lastname cannot be null")
	private String lastname;
	
	private String middlename;
	
	@NotNull(message = "firstname cannot be null")
	private String firstname;
	
	@NotNull(message="date of birth cannot be null")
	private String dob;
	
	@NotNull(message = "place of birth cannot be null")
	private String pob;
	
	@NotNull(message = "email cannot be null")
	private String email;
	
//	@NotNull(message = "address cannot be null")
	private String address;
	
	@NotNull(message = "number cannot be null")
	private String number;
	
	@NotNull(message = "gender cannot be null")
	private String gender;
	
//	@NotNull(message = "password cannot be null")
	private String password;
	
	
	private String status;
	
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
		this.email = email.toLowerCase();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
}

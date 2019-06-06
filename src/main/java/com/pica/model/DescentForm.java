package com.pica.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "descentForm")
public class DescentForm {

	@Id
	private int id;
	private Profile profile;
	private Dependents father;
	private Dependents mother;
	private Dependents paternalFather;
	private Dependents paternalMother;
	private Dependents maternalFather;
	private Dependents maternalMother;
	private String base29Code;
	private String appCode;
	private String custId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Dependents getFather() {
		return father;
	}

	public void setFather(Dependents father) {
		this.father = father;
	}

	public Dependents getMother() {
		return mother;
	}

	public void setMother(Dependents mother) {
		this.mother = mother;
	}

	public Dependents getPaternalFather() {
		return paternalFather;
	}

	public void setPaternalFather(Dependents paternalFather) {
		this.paternalFather = paternalFather;
	}

	public Dependents getPaternalMother() {
		return paternalMother;
	}

	public void setPaternalMother(Dependents paternalMother) {
		this.paternalMother = paternalMother;
	}

	public Dependents getMaternalFather() {
		return maternalFather;
	}

	public void setMaternalFather(Dependents maternalFather) {
		this.maternalFather = maternalFather;
	}

	public Dependents getMaternalMother() {
		return maternalMother;
	}

	public void setMaternalMother(Dependents maternalMother) {
		this.maternalMother = maternalMother;
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

	
}

package com.pica.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DeskClerk {

	private int _id;

	private String name;
	private ArrayList<Applicant> applications;
	
	private String formType;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Applicant> getApplications() {
		return applications;
	}

	public void setApplications(ArrayList<Applicant> applications) {
		this.applications = applications;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	@Override
	public String toString() {
		return "DeskClerk [_id=" + _id + ", name=" + name + ", applications=" + applications + ", formType=" + formType
				+ "]";
	}

	
	
}

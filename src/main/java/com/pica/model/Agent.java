package com.pica.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Agent {

	private int _id;

	private String name;
	private ArrayList<Applicant> applications;

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

}

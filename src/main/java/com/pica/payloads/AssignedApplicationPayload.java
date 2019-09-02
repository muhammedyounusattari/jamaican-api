package com.pica.payloads;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.pica.model.Applicant;

public class AssignedApplicationPayload {

	private  Integer agent;
	private String name;
	private String appType;
	private  List<Applicant> appCodes;
	
	public AssignedApplicationPayload() {
		this.agent = null;
		this.appCodes = Collections.emptyList();
	}
	
	public Integer getAgent() {
		return agent;
	}
	public void setAgent(Integer agent) {
		this.agent = agent;
	}
	public List<Applicant> getAppCodes() {
		return appCodes;
	}
	public void setAppCodes(List<Applicant> appCodes) {
		this.appCodes = appCodes;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}
	
	
}

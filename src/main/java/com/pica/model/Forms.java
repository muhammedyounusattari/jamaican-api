package com.pica.model;

import com.pica.commons.PICAApplictions;

public  class Forms{
	private String formName;
	private String formStatus;
	private String formAppCode;
	private PICAApplictions applied;
	private String appliedDate;
	private String appliedFor;
	private String custId;
	private String base29Code;
	
	public String getFormStatus() {
		return formStatus;
	}
	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}
	public String getFormAppCode() {
		return formAppCode;
	}
	public void setFormAppCode(String formAppCode) {
		this.formAppCode = formAppCode;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public PICAApplictions getApplied() {
		return applied;
	}
	public void setApplied(PICAApplictions applied) {
		this.applied = applied;
	}
	public String getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}
	public String getAppliedFor() {
		return appliedFor;
	}
	public void setAppliedFor(String appliedFor) {
		this.appliedFor = appliedFor;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getBase29Code() {
		return base29Code;
	}
	public void setBase29Code(String base29Code) {
		this.base29Code = base29Code;
	}
	
}

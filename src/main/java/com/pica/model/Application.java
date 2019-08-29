package com.pica.model;

public class Application {

	final private String type;
	final private String status;
	final private String appCode;
	final private String custId;
	final private String base29Code;

	public Application(String type, String status, String appCode, String custId, String base29Code) {
		super();
		this.type = type;
		this.status = status;
		this.appCode = appCode;
		this.custId = custId;
		this.base29Code = base29Code;
	}

	public String getType() {
		return type;
	}

	public String getStatus() {
		return status;
	}

	public String getAppCode() {
		return appCode;
	}

	public String getCustId() {
		return custId;
	}

	public String getBase29Code() {
		return base29Code;
	}

}

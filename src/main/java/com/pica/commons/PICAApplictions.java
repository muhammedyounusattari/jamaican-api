package com.pica.commons;

public enum PICAApplictions {
	CJA("Certificate of Jamican Citizenship"), DPA("Descent Process Application"),
	JCRM("Jamaican Citizenship Registration Minor"), NACW("Naturalization Aliens Common Wealth"),
	RVM("Registration by Virtue of Marriage"), RCWC("Registration of Common Wealth Citizens"),
	RGJC("Registration of Jamaican Citizenship"), RJC("Renunciation of Jamaican Citizenship"),
	RC("Restoration of Citizenship");

	private String application;

	PICAApplictions(String application){
		this.application = application;
	}
	
	public String getApplication() {
		return application;
	}
	
	public static void main(String[] args) {
		System.out.println(PICAApplictions.CJA.getApplication());
	}
}

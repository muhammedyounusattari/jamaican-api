package com.pica.commons;

import java.util.HashMap;
import java.util.Map;

public enum FormStatus {


	SUBMITTED("submitted"), PROCESSING("processing"), INCOMPLETE("incomplete"), DECLINED("declined"),SCHEDULE("schedule"),
	REFFERED("reffered"), COMPLETED("completed"), PAS("pas"),CS("cs"),REVIEW("review"),OM("om"),DIRECTOR("director"),CEO("ceo"),PERMANENTSECRETARY("permanentsecretary");

	private static final Map<String, FormStatus> map = new HashMap<>();

	static {
		for (FormStatus enums : values()) {
			map.put(enums.getStatus(), enums);
		}
	}

	private String status;

	FormStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public static FormStatus getByValue(String status) {
		return map.get(status);
	}

	public static void main(String[] args) {
		System.out.println(FormStatus.getByValue("processing").getStatus());
	}
}

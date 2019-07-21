package com.pica.payloads;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ResponsePayload {

	private List<?> data;
	private HttpStatus status;
	

	public ResponsePayload(List<?> data, HttpStatus status) {
		super();
		this.data = data;
		this.status = status;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}

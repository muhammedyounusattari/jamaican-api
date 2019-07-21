package com.pica.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Supervisor {

	private Agent agent;

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Agent getAgent() {
		return this.agent;
	}

}

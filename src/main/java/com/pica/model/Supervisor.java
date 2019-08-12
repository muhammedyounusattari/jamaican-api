package com.pica.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Supervisor {

	private Agent agent;
	private DeskClerk deskClerk;

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Agent getAgent() {
		return this.agent;
	}

	public DeskClerk getDeskClerk() {
		return deskClerk;
	}

	public void setDeskClerk(DeskClerk deskClerk) {
		this.deskClerk = deskClerk;
	}

}

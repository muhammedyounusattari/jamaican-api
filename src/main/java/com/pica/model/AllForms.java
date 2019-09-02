package com.pica.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="allForms")
public class AllForms {

	//@Id
	private String id;
	private String name;
	private String link;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}

package com.pica.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UploadDocuments {

	private List<MultipartFile> documentList;
	private Integer id;

	public List<MultipartFile> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<MultipartFile> documentList) {
		this.documentList = documentList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}

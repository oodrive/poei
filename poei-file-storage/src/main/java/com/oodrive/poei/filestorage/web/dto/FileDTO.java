package com.oodrive.poei.filestorage.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class FileDTO {

	private int fileId;

	private String name;

	private String path;

	public FileDTO() {
	}

	public FileDTO(int fileId, String name, String path) {
		this.fileId = fileId;
		this.name = name;
		this.path = path;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}

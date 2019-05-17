package com.oodrive.poei.filestorage.web.dto;

import org.springframework.core.io.Resource;


public class FileResponseDTO {
	private final String name;
	private final Resource resource;

	public FileResponseDTO(String name, Resource resource) {
		this.name = name;
		this.resource = resource;
	}

	public String getName() {
		return name;
	}

	public Resource getResource() {
		return resource;
	}
}

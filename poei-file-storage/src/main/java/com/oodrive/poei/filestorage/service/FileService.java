package com.oodrive.poei.filestorage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.oodrive.poei.filestorage.dao.FileDAO;
import com.oodrive.poei.filestorage.web.dto.FileDTO;


public class FileService {

	private final FileDAO fileDAO;

	public FileService(FileDAO fileDAO) {this.fileDAO = fileDAO;}

	@Transactional
	public Integer save(String name, String path) {
		return fileDAO.save(name, path);
	}

	@Transactional(readOnly = true)
	public List<FileDTO> findAll() {
		return fileDAO.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<FileDTO> findById(int fileId) {
		return fileDAO.findById(fileId);
	}

	@Transactional(readOnly = true)
	public Optional<FileDTO> findByName(String name) {
		return fileDAO.findByName(name);
	}
}

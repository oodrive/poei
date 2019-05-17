package com.oodrive.poei.filestorage.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oodrive.poei.filestorage.service.FileService;
import com.oodrive.poei.filestorage.storage.FileStorage;
import com.oodrive.poei.filestorage.web.dto.FileDTO;
import com.oodrive.poei.filestorage.web.dto.FileResponseDTO;


@RestController
@RequestMapping(path = "/file")
public class FileController {

	private final FileStorage fileStorage;

	private final FileService fileService;

	@Autowired
	public FileController(FileStorage fileStorage, FileService fileService) {
		this.fileStorage = fileStorage;
		this.fileService = fileService;
	}

	@PostMapping
	public FileDTO upload(@RequestParam("file") MultipartFile file) {
		return fileStorage.store(file);
	}

	@GetMapping
	public List<FileDTO> findAll() {
		return fileService.findAll();
	}

	@GetMapping(path = "/{fileId}")
	public ResponseEntity<Resource> download(@PathVariable int fileId) {
		FileResponseDTO fileResponseDTO = fileStorage.download(fileId);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + fileResponseDTO.getName() + "\"").body(fileResponseDTO.getResource());
	}
}

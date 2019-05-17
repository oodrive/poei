package com.oodrive.poei.filestorage.storage;

import org.springframework.web.multipart.MultipartFile;

import com.oodrive.poei.filestorage.web.dto.FileDTO;
import com.oodrive.poei.filestorage.web.dto.FileResponseDTO;


public interface FileStorage {

	FileDTO store(MultipartFile file);

	FileResponseDTO download(int fileId);
}

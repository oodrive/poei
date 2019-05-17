package com.oodrive.poei.filestorage.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.springframework.core.io.InputStreamResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.oodrive.poei.filestorage.crypto.operator.CipherStreamSupplier;
import com.oodrive.poei.filestorage.exception.FileNotFoundException;
import com.oodrive.poei.filestorage.exception.StorageException;
import com.oodrive.poei.filestorage.service.FileService;
import com.oodrive.poei.filestorage.web.dto.FileDTO;
import com.oodrive.poei.filestorage.web.dto.FileResponseDTO;


public class FileSystemStorage implements FileStorage {

	private static final int BUFFER_SIZE = 8192;

	private final FileService fileService;

	private final CipherStreamSupplier cipherStreamSupplier;

	public FileSystemStorage(FileService fileService,
			CipherStreamSupplier cipherStreamSupplier) {
		this.fileService = fileService;
		this.cipherStreamSupplier = cipherStreamSupplier;
	}

	@Transactional
	@Override
	public FileDTO store(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				// This is a security check
				throw new StorageException(
						"Cannot store file with relative path outside current directory "
								+ filename);
			}
			Path path;
			Optional<FileDTO> optionalFileDTO = fileService.findByName(filename);
			if (optionalFileDTO.isPresent()) {
				path = new File(optionalFileDTO.get().getPath()).toPath();
			} else {
				path = Files.createTempFile(filename, "");
			}
			try (InputStream inputStream = file.getInputStream();
					OutputStream outputStream = new FileOutputStream(path.toFile());
					OutputStream cipherOutputStream = cipherStreamSupplier.wrapOutputStream(outputStream)
			) {
				copy(inputStream, cipherOutputStream);
			}
			String absolutePath = path.toAbsolutePath().toString();
			Integer fileId = optionalFileDTO.map(FileDTO::getFileId)
					.orElseGet(() -> fileService.save(filename, absolutePath));
			return new FileDTO(fileId, filename, absolutePath);
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + filename, e);
		}
	}

	@Override
	public FileResponseDTO download(int fileId) {
		FileDTO fileDTO = fileService.findById(fileId).orElseThrow(FileNotFoundException::new);
		Path path = new File(fileDTO.getPath()).toPath();

		try {
			InputStream input = new FileInputStream(path.toFile());
			InputStream cipherInput = cipherStreamSupplier.wrapInputStream(input);
			return new FileResponseDTO(fileDTO.getName(), new InputStreamResource(cipherInput));
		} catch (IOException e) {
			throw new StorageException("Failed to fetch file " + fileDTO.getName(), e);
		}
	}

	private static long copy(InputStream source, OutputStream sink) throws IOException
	{
		long nread = 0L;
		byte[] buf = new byte[BUFFER_SIZE];
		int n;
		while ((n = source.read(buf)) > 0) {
			sink.write(buf, 0, n);
			nread += n;
		}
		return nread;
	}
}

package com.oodrive.poei.filestorage.config;

import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.oodrive.poei.filestorage.crypto.hasher.Hasher;
import com.oodrive.poei.filestorage.crypto.hasher.Sha256Hasher;
import com.oodrive.poei.filestorage.crypto.operator.AESCipherStreamSupplier;
import com.oodrive.poei.filestorage.crypto.operator.CipherStreamSupplier;
import com.oodrive.poei.filestorage.dao.FileDAO;
import com.oodrive.poei.filestorage.service.FileService;
import com.oodrive.poei.filestorage.storage.FileStorage;
import com.oodrive.poei.filestorage.storage.FileSystemStorage;


@Configuration
public class FileStorageConfig {

	@Bean
	FileDAO fileDAO(JdbcTemplate jdbcTemplate) {
		return new FileDAO(jdbcTemplate);
	}

	@Bean
	FileService fileService(FileDAO fileDAO) {
		return new FileService(fileDAO);
	}

	@Bean
	FileStorage fileStorage(FileService fileService, CipherStreamSupplier cipherStreamSupplier) {
		return new FileSystemStorage(fileService, cipherStreamSupplier);
	}

	@Bean
	Hasher hasher() {
		return new Sha256Hasher();
	}

	@Bean
	SecretKey secretKey(Hasher hasher, @Value("poei.private-key") String privateKey) {
		byte[] privateKeyBytes = hasher.hash(privateKey);
		// AES only accept keys with 16, 24 or 32 bytes length
		byte[] result = Arrays.copyOf(privateKeyBytes, 32);
		return new SecretKeySpec(result, "AES");
	}

	@Bean
	CipherStreamSupplier contentCryptographicOperator(SecretKey secretKey) {
		return new AESCipherStreamSupplier(secretKey);
	}
}

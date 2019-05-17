package com.oodrive.poei.filestorage.crypto.hasher;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Sha256Hasher implements Hasher {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sha256Hasher.class);

	private static final MessageDigest DIGEST;

	static {
		try {
			DIGEST = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public byte[] hash(String str) {
		return DIGEST.digest(str.getBytes(StandardCharsets.UTF_8));
	}
}

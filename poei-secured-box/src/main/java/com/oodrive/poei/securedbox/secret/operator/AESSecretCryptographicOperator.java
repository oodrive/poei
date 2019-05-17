package com.oodrive.poei.securedbox.secret.operator;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oodrive.poei.securedbox.exception.InvalidPasswordException;


public class AESSecretCryptographicOperator implements SecretCryptographicOperator {

	private static final Logger LOGGER = LoggerFactory.getLogger(AESSecretCryptographicOperator.class);

	private static final String ALGORITHM = "AES";

	private final SecretKeyGenerator secretKeyGenerator;

	public AESSecretCryptographicOperator(SecretKeyGenerator secretKeyGenerator) {
		this.secretKeyGenerator = secretKeyGenerator;
	}

	@Override
	public byte[] encrypt(String message, char[] password, byte[] salt) {
		return perform(message.getBytes(StandardCharsets.UTF_8), password, salt, Cipher.ENCRYPT_MODE);
	}

	@Override
	public String decrypt(byte[] encryptedMsg, char[] password, byte[] salt) {
		byte[] decryptedMsg = perform(encryptedMsg, password, salt, Cipher.DECRYPT_MODE);
		return new String(decryptedMsg, StandardCharsets.UTF_8);
	}

	private byte[] perform(byte[] encryptedMsg, char[] password, byte[] salt, int decryptMode) {
		SecretKey key = secretKeyGenerator.generate(password, salt);
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new RuntimeException(e);
		}
		try {
			cipher.init(decryptMode, key);
		} catch (InvalidKeyException e) {
			LOGGER.warn(e.getMessage());
			throw new InvalidPasswordException();
		}
		byte[] result;
		try {
			result = cipher.doFinal(encryptedMsg);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			LOGGER.warn(e.getMessage());
			throw new InvalidPasswordException();
		}
		return result;
	}
}

package com.oodrive.poei.filestorage.crypto.operator;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import com.oodrive.poei.filestorage.exception.CipherOperationException;



public class AESCipherStreamSupplier implements CipherStreamSupplier {

	private static final String ALGORITHM = "AES";

	private final SecretKey secretKey;

	public AESCipherStreamSupplier(SecretKey secretKey) {
		this.secretKey = secretKey;
	}

	@Override
	public OutputStream wrapOutputStream(OutputStream output) {
		Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
		return new CipherOutputStream(output, cipher);
	}

	@Override
	public InputStream wrapInputStream(InputStream input) {
		Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
		return new CipherInputStream(input, cipher);
	}

	private Cipher getCipher(int decryptMode) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new CipherOperationException(e);
		}
		try {
			cipher.init(decryptMode, secretKey);
		} catch (InvalidKeyException e) {
			throw new CipherOperationException(e);
		}
		return cipher;
	}
}

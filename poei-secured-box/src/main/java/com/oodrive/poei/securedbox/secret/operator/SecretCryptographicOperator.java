package com.oodrive.poei.securedbox.secret.operator;

public interface SecretCryptographicOperator {

	byte[] encrypt(String message, char[] password, byte[] salt);

	String decrypt(byte[] encryptedMsg, char[] password, byte[] salt);
}

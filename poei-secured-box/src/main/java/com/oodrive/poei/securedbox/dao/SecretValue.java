package com.oodrive.poei.securedbox.dao;

public class SecretValue {
	private final byte[] value;
	private final byte[] salt;

	public SecretValue(byte[] value, byte[] salt) {
		this.value = value;
		this.salt = salt;
	}

	public byte[] getValue() {
		return value;
	}

	public byte[] getSalt() {
		return salt;
	}
}

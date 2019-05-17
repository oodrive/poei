package com.oodrive.poei.passwordmanager.web.dto;

import java.util.Arrays;


public class PasswordDTO {

	private char[] value;

	public PasswordDTO() {
	}

	public PasswordDTO(char[] value) {
		this.value = value;
	}

	public char[] getValue() {
		if (value == null) {
			throw new IllegalStateException("password has been cleared");
		}
		return value.clone();
	}

	public void setValue(char[] password) {
		this.value = Arrays.copyOf(password, password.length);
	}

	public void obfuscate() {
		if (value != null) {
			Arrays.fill(value, ' ');
			value = null;
		}
	}
}

package com.oodrive.poei.securedbox.web.dto;

import java.util.Arrays;


public class WriteSecret {

	private String key;

	private String value;

	private char[] password;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public char[] getPassword() {
		if (password == null) {
			throw new IllegalStateException("password has been cleared");
		}
		return password.clone();
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public void obfuscatePassword() {
		if (password != null) {
			Arrays.fill(password, ' ');
			password = null;
		}
	}
}

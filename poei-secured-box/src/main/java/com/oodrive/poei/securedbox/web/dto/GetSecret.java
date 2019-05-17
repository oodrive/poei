package com.oodrive.poei.securedbox.web.dto;

import java.util.Arrays;


public class GetSecret {

	private char[] password;

	public char[] getPassword() {
		if (password == null) {
			throw new IllegalStateException("password has been cleared");
		}
		return password.clone();
	}

	public void setPassword(char[] password) {
		this.password = Arrays.copyOf(password, password.length);
	}

	public void obfuscatePassword() {
		if (password != null) {
			Arrays.fill(password, ' ');
			password = null;
		}
	}
}

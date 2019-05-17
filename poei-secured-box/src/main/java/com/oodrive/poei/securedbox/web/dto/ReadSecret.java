package com.oodrive.poei.securedbox.web.dto;

public class ReadSecret {
	private final String value;

	public ReadSecret(String value) {this.value = value;}

	public String getValue() {
		return value;
	}
}

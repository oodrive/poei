package com.oodrive.poei.securedbox.secret.salt;

import java.security.SecureRandom;


public class RandomSaltGenerator implements SaltGenerator {

	@Override
	public byte[] generate() {
		byte[] salt = new byte[32];
		new SecureRandom().nextBytes(salt);
		return salt;
	}
}

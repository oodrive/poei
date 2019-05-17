package com.oodrive.poei.securedbox.secret.operator;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.oodrive.poei.securedbox.secret.hasher.Hasher;


public class Simple32BytesAESSecretKeyGenerator implements SecretKeyGenerator {

	private static final int KEY_SIZE = 32;

	private final Hasher hasher;

	public Simple32BytesAESSecretKeyGenerator(Hasher hasher) {this.hasher = hasher;}

	@Override
	public SecretKey generate(char[] privateKey, byte[] salt) {
		String saltedPassword = new String(salt, StandardCharsets.UTF_8) + new String(privateKey);
		byte[] privateKeyBytes = hasher.hash(saltedPassword);
		// AES only accept keys with 16, 24 or 32 bytes length
		byte[] result = Arrays.copyOf(privateKeyBytes, KEY_SIZE);
		return new SecretKeySpec(result, "AES");
	}
}

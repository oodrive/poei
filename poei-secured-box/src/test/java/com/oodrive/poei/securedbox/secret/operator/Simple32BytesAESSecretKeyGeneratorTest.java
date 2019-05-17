package com.oodrive.poei.securedbox.secret.operator;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.junit.Assert;
import org.junit.Test;

import com.oodrive.poei.securedbox.secret.hasher.Sha256Hasher;


public class Simple32BytesAESSecretKeyGeneratorTest {

	private static final char[] PRIVATE_KEY = "foobar".toCharArray();
	private static final byte[] SALT = "Salty".getBytes(StandardCharsets.UTF_8);

	@Test
	public void generate() {
		SecretKeyGenerator secretKeyGenerator = new Simple32BytesAESSecretKeyGenerator(new Sha256Hasher());

		SecretKey secretKey = secretKeyGenerator.generate(PRIVATE_KEY, SALT);

		Assert.assertNotNull(secretKey);
		Assert.assertEquals("AES", secretKey.getAlgorithm());
		Assert.assertEquals("RAW", secretKey.getFormat());
		Assert.assertEquals(32, secretKey.getEncoded().length);
	}
}

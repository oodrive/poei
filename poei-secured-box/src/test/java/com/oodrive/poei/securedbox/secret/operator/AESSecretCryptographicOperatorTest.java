package com.oodrive.poei.securedbox.secret.operator;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Assert;
import org.junit.Test;

import com.oodrive.poei.securedbox.secret.hasher.Sha256Hasher;


public class AESSecretCryptographicOperatorTest {

	private static final char[] PASSWORD_1 = "foobar".toCharArray();

	private static final char[] PASSWORD_2 =
			"azertyuiopqsdfghjkmlmwxcvbnazertyuiop azd azd SQDQDZA SQdsqd 12162 23 29832 219830".toCharArray();

	private static final String BASE64_SECRET_1 = "tzFbLg5W6VrcN7S/Sqn7xA==";

	private static final String BASE64_SECRET_2 = "3P1OSIYKjFyrzsl1drfXvw==";

	private static final String SECRET = "Hello, world";

	private static final byte[] SALT = "Salty".getBytes(StandardCharsets.UTF_8);

	@Test
	public void encrypt() {
		SecretCryptographicOperator operator = new AESSecretCryptographicOperator(new Simple32BytesAESSecretKeyGenerator(new Sha256Hasher()));

		byte[] encryptedSecret1 = operator.encrypt(SECRET, PASSWORD_1, SALT);
		byte[] encryptedSecret2 = operator.encrypt(SECRET, PASSWORD_2, SALT);
		Base64.Encoder base64Encoder = Base64.getEncoder();

		Assert.assertNotNull(encryptedSecret1);
		Assert.assertNotNull(encryptedSecret2);
		Assert.assertNotEquals(encryptedSecret1, encryptedSecret2);
		Assert.assertEquals(BASE64_SECRET_1, base64Encoder.encodeToString(encryptedSecret1));
		Assert.assertEquals(BASE64_SECRET_2, base64Encoder.encodeToString(encryptedSecret2));
	}

	@Test
	public void decrypt() {
		SecretCryptographicOperator operator = new AESSecretCryptographicOperator(new Simple32BytesAESSecretKeyGenerator(new Sha256Hasher()));

		Base64.Decoder base64Decoder = Base64.getDecoder();
		String decryptedMessage1 = operator.decrypt(base64Decoder.decode(BASE64_SECRET_1), PASSWORD_1, SALT);
		String decryptedMessage2 = operator.decrypt(base64Decoder.decode(BASE64_SECRET_2), PASSWORD_2, SALT);

		Assert.assertNotNull(decryptedMessage1);
		Assert.assertEquals(SECRET, decryptedMessage1);
		Assert.assertNotNull(decryptedMessage2);
		Assert.assertEquals(SECRET, decryptedMessage2);
	}
}

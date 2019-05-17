package com.oodrive.poei.securedbox.secret.salt;

import org.junit.Assert;
import org.junit.Test;


public class RandomSaltGeneratorTest {
	@Test
	public void generate() {
		SaltGenerator saltGenerator = new RandomSaltGenerator();
		for (int i = 0; i < 100; i++) {
			byte[] salt = saltGenerator.generate();
			Assert.assertNotNull(salt);
			Assert.assertEquals(32, salt.length);
		}
	}
}

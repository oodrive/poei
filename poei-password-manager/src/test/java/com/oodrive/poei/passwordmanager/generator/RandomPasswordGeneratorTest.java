package com.oodrive.poei.passwordmanager.generator;

import org.junit.Assert;
import org.junit.Test;


public class RandomPasswordGeneratorTest {

	@Test
	public void generate() {
		// GIVEN
		int length = 20;

		// WHEN
		PasswordGenerator passwordGenerator = RandomPasswordGenerator.Builder.start()
				.useAlphaNumerics()
				.useCustom("*#~")
				.withLength(length)
				.build();
		for (int i = 0; i < 100; i++) {
			char[] pwd = passwordGenerator.generate();

			// THEN
			Assert.assertNotNull(pwd);
			Assert.assertEquals(length, pwd.length);
		}
	}

	@Test
	public void generated_empty() {
		// WHEN
		PasswordGenerator passwordGenerator = RandomPasswordGenerator.Builder.start()
				.build();
		char[] pwd = passwordGenerator.generate();

		// THEN
		Assert.assertNotNull(pwd);
		Assert.assertNotEquals("", new String(pwd).trim());
	}
}

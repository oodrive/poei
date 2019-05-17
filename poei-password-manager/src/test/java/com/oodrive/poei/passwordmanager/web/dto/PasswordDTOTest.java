package com.oodrive.poei.passwordmanager.web.dto;

import org.junit.Assert;
import org.junit.Test;


public class PasswordDTOTest {

	@Test(expected = IllegalStateException.class)
	public void obfuscate() {
		PasswordDTO password = new PasswordDTO("foobar".toCharArray());
		Assert.assertNotNull(password.getValue());
		password.obfuscate();
		password.getValue();
	}
}

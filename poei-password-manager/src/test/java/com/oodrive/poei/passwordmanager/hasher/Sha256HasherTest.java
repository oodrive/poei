package com.oodrive.poei.passwordmanager.hasher;

import org.junit.Assert;
import org.junit.Test;


public class Sha256HasherTest {

	@Test
	public void hash() {
		Hasher hasher = new Sha256Hasher();

		Assert.assertEquals("SufDtqwL7/Zx76jPVzhhUcBuWMpTp42D82EHMWzsEl8=", hasher.hash("Hello, world"));
	}
}

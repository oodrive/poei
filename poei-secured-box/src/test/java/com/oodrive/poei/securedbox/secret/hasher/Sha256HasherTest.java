package com.oodrive.poei.securedbox.secret.hasher;

import java.util.Base64;

import org.junit.Assert;
import org.junit.Test;


public class Sha256HasherTest {

	@Test
	public void hash() {
		Hasher hasher = new Sha256Hasher();

		Assert.assertEquals("SufDtqwL7/Zx76jPVzhhUcBuWMpTp42D82EHMWzsEl8=",
				Base64.getEncoder().encodeToString(hasher.hash("Hello, world")));
	}
}

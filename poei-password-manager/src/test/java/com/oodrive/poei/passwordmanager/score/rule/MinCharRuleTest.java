package com.oodrive.poei.passwordmanager.score.rule;

import org.junit.Assert;
import org.junit.Test;


public class MinCharRuleTest {

	@Test
	public void isValid() {
		Rule rule = new MinCharRule(4);

		Assert.assertFalse(rule.isValid(null));
		Assert.assertFalse(rule.isValid("".toCharArray()));
		Assert.assertFalse(rule.isValid("abc1234".toCharArray()));
		Assert.assertFalse(rule.isValid("1b234".toCharArray()));
		Assert.assertTrue(rule.isValid("1b2abc34".toCharArray()));
		Assert.assertTrue(rule.isValid("1b2aBc34".toCharArray()));
		Assert.assertTrue(rule.isValid("1b2aBc34POlqsdaAZ".toCharArray()));
	}
}

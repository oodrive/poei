package com.oodrive.poei.passwordmanager.score.rule;

import org.junit.Assert;
import org.junit.Test;


public class MinDigitRuleTest {

	@Test
	public void isValid() {
		Rule rule = new MinDigitRule(4);

		Assert.assertFalse(rule.isValid(null));
		Assert.assertFalse(rule.isValid("".toCharArray()));
		Assert.assertFalse(rule.isValid("abc123".toCharArray()));
		Assert.assertFalse(rule.isValid("1b23c".toCharArray()));
		Assert.assertTrue(rule.isValid("1b2a34".toCharArray()));
		Assert.assertTrue(rule.isValid("1b2ac34".toCharArray()));
		Assert.assertTrue(rule.isValid("1b2aBc34POlqsdaAZ".toCharArray()));
	}
}

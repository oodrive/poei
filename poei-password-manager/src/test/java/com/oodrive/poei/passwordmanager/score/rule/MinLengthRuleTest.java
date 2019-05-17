package com.oodrive.poei.passwordmanager.score.rule;

import org.junit.Assert;
import org.junit.Test;


public class MinLengthRuleTest {

	@Test
	public void isValid() {
		Rule rule = new MinLengthRule(4);

		Assert.assertFalse(rule.isValid(null));
		Assert.assertFalse(rule.isValid("".toCharArray()));
		Assert.assertFalse(rule.isValid("1".toCharArray()));
		Assert.assertFalse(rule.isValid("1a".toCharArray()));
		Assert.assertFalse(rule.isValid("1ab".toCharArray()));
		Assert.assertTrue(rule.isValid("1ab2".toCharArray()));
		Assert.assertTrue(rule.isValid("1ab2456".toCharArray()));
	}
}

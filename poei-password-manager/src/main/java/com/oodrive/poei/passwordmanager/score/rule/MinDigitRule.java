package com.oodrive.poei.passwordmanager.score.rule;

public class MinDigitRule implements Rule {

	private final Rule minRule;

	public MinDigitRule(int min) {
		minRule = new MinRule(min, Character::isDigit);
	}

	@Override
	public boolean isValid(char[] password) {
		return minRule.isValid(password);
	}

	@Override
	public ScoreWeight weight() {
		return ScoreWeight.LOW;
	}
}

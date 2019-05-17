package com.oodrive.poei.passwordmanager.score.rule;

public class MinCharRule implements Rule {

	private final Rule minRule;

	public MinCharRule(int min) {
		minRule = new MinRule(min, Character::isLetter);
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

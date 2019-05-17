package com.oodrive.poei.passwordmanager.score.rule;

public class MinLengthRule implements Rule {

	private final int minLength;

	public MinLengthRule(int minLength) {
		if (minLength < 0) {
			throw new IllegalArgumentException("MinLength must be positive");
		}
		this.minLength = minLength;
	}

	@Override
	public boolean isValid(char[] password) {
		if (password == null) {
			return false;
		}
		return minLength <= password.length;
	}

	@Override
	public ScoreWeight weight() {
		return ScoreWeight.HIGH;
	}
}

package com.oodrive.poei.passwordmanager.score.rule;

import java.util.function.Predicate;


public class MinRule implements Rule {

	private final int min;

	private final Predicate<Character> predicate;

	public MinRule(int min, Predicate<Character> predicate) {
		if (min < 0) {
			throw new IllegalArgumentException("Min must be positive");
		}
		if (predicate == null) {
			throw new IllegalArgumentException("Predicate cannot be null");
		}
		this.min = min;
		this.predicate = predicate;
	}

	@Override
	public boolean isValid(char[] password) {
		if (password == null) {
			return false;
		}
		int count = 0;
		for (char c : password) {
			if (predicate.test(c)) {
				count++;
				if (count >= min) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public ScoreWeight weight() {
		return ScoreWeight.LOW;
	}
}

package com.oodrive.poei.passwordmanager.score.rule;

import com.oodrive.poei.passwordmanager.service.PasswordService;


public class NotUsedRule implements Rule {

	private final PasswordService passwordService;

	public NotUsedRule(PasswordService passwordService) {this.passwordService = passwordService;}

	@Override
	public boolean isValid(char[] password) {
		return !passwordService.exists(password);
	}

	@Override
	public ScoreWeight weight() {
		return ScoreWeight.MEDIUM;
	}
}

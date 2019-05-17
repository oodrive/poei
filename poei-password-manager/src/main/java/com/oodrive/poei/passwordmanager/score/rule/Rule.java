package com.oodrive.poei.passwordmanager.score.rule;

public interface Rule {

	boolean isValid(char[] password);

	/**
	 * The score weight is used to weight the importance of this rule for the final score computation
	 * @return the weight of this rule on the final password scoring
	 */
	ScoreWeight weight();
}

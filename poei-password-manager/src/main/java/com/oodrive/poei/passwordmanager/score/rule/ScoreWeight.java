package com.oodrive.poei.passwordmanager.score.rule;

public enum ScoreWeight {
	// Arbitrary values
	LOW(10f),
	MEDIUM(25f),
	HIGH(35f);

	public final float score;

	ScoreWeight(float score) {
		this.score = score;
	}
}

package com.oodrive.poei.passwordmanager.score;

public enum Score {
	LOW,
	MEDIUM,
	HIGH;

	static Score from(float scoreInPercent) {
		float low = 100f / (float) Score.values().length;
		float high = low * 2f;

		if (scoreInPercent <= low) {
			return Score.LOW;
		} else if (scoreInPercent >= high) {
			return Score.HIGH;
		}
		return Score.MEDIUM;
	}
}

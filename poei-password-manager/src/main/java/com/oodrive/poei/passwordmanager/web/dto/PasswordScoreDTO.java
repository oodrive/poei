package com.oodrive.poei.passwordmanager.web.dto;

import com.oodrive.poei.passwordmanager.score.Score;


public class PasswordScoreDTO {
	private Score score;

	public PasswordScoreDTO() {
	}

	public PasswordScoreDTO(Score score) {
		this.score = score;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}
}

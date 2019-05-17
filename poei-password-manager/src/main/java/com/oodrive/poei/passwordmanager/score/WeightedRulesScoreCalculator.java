package com.oodrive.poei.passwordmanager.score;

import java.util.List;

import com.oodrive.poei.passwordmanager.score.rule.Rule;


public class WeightedRulesScoreCalculator implements ScoreCalculator {

	private final List<Rule> rules;

	public WeightedRulesScoreCalculator(List<Rule> rules) {this.rules = rules;}

	@Override
	public Score compute(char[] password) {
		float scoreInPercent = 0f;
		for (Rule rule : rules) {
			if (rule.isValid(password)) {
				scoreInPercent += rule.weight().score;
			}
		}
		return Score.from(scoreInPercent);
	}
}

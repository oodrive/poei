package com.oodrive.poei.passwordmanager.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.oodrive.poei.passwordmanager.dao.PasswordDAO;
import com.oodrive.poei.passwordmanager.hasher.Hasher;
import com.oodrive.poei.passwordmanager.hasher.Sha256Hasher;
import com.oodrive.poei.passwordmanager.score.ScoreCalculator;
import com.oodrive.poei.passwordmanager.score.WeightedRulesScoreCalculator;
import com.oodrive.poei.passwordmanager.score.rule.MinCharRule;
import com.oodrive.poei.passwordmanager.score.rule.MinDigitRule;
import com.oodrive.poei.passwordmanager.score.rule.MinLengthRule;
import com.oodrive.poei.passwordmanager.score.rule.NotUsedRule;
import com.oodrive.poei.passwordmanager.score.rule.Rule;
import com.oodrive.poei.passwordmanager.service.PasswordService;


@Configuration
public class PasswordManagerConfig {

	@Bean
	Hasher sha256Hasher() {
		return new Sha256Hasher();
	}

	@Bean
	PasswordDAO passwordDAO(JdbcTemplate jdbcTemplate) {
		return new PasswordDAO(jdbcTemplate);
	}

	@Bean
	PasswordService passwordService(PasswordDAO passwordDAO, Hasher hasher) {
		return new PasswordService(passwordDAO, hasher);
	}

	@Bean
	Rule minCharRule() {
		return new MinCharRule(3);
	}

	@Bean
	Rule minDigitRule() {
		return new MinDigitRule(3);
	}

	@Bean
	Rule minLengthRule() {
		return new MinLengthRule(8);
	}

	@Bean
	Rule notUsedRule(PasswordService passwordService) {
		return new NotUsedRule(passwordService);
	}

	@Bean
	ScoreCalculator passwordComplexityChecker(List<Rule> rules) {
		return new WeightedRulesScoreCalculator(rules);
	}
}

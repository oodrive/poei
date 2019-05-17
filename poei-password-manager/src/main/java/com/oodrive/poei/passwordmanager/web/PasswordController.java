package com.oodrive.poei.passwordmanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oodrive.poei.passwordmanager.generator.PasswordGenerator;
import com.oodrive.poei.passwordmanager.generator.RandomPasswordGenerator;
import com.oodrive.poei.passwordmanager.score.Score;
import com.oodrive.poei.passwordmanager.score.ScoreCalculator;
import com.oodrive.poei.passwordmanager.service.PasswordService;
import com.oodrive.poei.passwordmanager.web.dto.PasswordDTO;
import com.oodrive.poei.passwordmanager.web.dto.PasswordScoreDTO;


@RestController
public class PasswordController {

	private final ScoreCalculator scoreCalculator;

	private final PasswordService passwordService;

	@Autowired
	public PasswordController(ScoreCalculator scoreCalculator,
			PasswordService passwordService) {
		this.scoreCalculator = scoreCalculator;
		this.passwordService = passwordService;
	}

	@GetMapping(path = "/random")
	public PasswordDTO generateRandom(@RequestParam(required = false) Integer length,
			@RequestParam(required = false, defaultValue = "true") boolean useChars,
			@RequestParam(required = false, defaultValue = "true") boolean useDigits) {
		RandomPasswordGenerator.Builder builder = RandomPasswordGenerator.Builder.start();
		if (useChars) {
			builder.useChars();
		}
		if (useDigits) {
			builder.useDigits();
		}
		if (length != null) {
			builder.withLength(length);
		}
		PasswordGenerator passwordGenerator = builder.build();
		return new PasswordDTO(passwordGenerator.generate());
	}

	@PostMapping(path = "/score")
	public PasswordScoreDTO computeScore(@RequestBody PasswordDTO password) {
		Score score = scoreCalculator.compute(password.getValue());
		passwordService.create(password.getValue());
		// Prevent password to be kept in memory
		password.obfuscate();
		return new PasswordScoreDTO(score);
	}
}

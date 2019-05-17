package com.oodrive.poei.passwordmanager.generator;

import java.security.SecureRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RandomPasswordGenerator implements PasswordGenerator {

	private static final Logger LOGGER = LoggerFactory.getLogger(RandomPasswordGenerator.class);

	private final int length;

	private final char[] symbols;

	public RandomPasswordGenerator(int length, char[] symbols) {
		this.length = length;
		this.symbols = symbols;
	}

	@Override
	public char[] generate() {
		char[] result = new char[length];
		for (int i = 0; i < length; i++) {
			result[i] = symbols[randomInt(symbols.length - 1)];
		}
		return result;
	}

	private static int randomInt(int max) {
		return new SecureRandom().nextInt(max + 1);
	}

	public static class Builder {

		private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		private static final String LOWER = UPPER.toLowerCase();

		private static final String DIGITS = "0123456789";

		private static final int DEFAULT_LENGTH = 21;

		private StringBuilder symbols = new StringBuilder();

		private int length = -1;

		public static Builder start() {
			return new Builder();
		}

		public Builder useChars() {
			symbols.append(UPPER)
					.append(LOWER);
			return this;
		}

		public Builder useDigits() {
			symbols.append(DIGITS);
			return this;
		}

		public Builder useAlphaNumerics() {
			return useChars().useDigits();
		}

		public Builder useCustom(String custom) {
			symbols.append(custom);
			return this;
		}

		public Builder withLength(int length) {
			this.length = length;
			return this;
		}

		public PasswordGenerator build() {
			if (symbols.length() <= 0) {
				LOGGER.warn("No symbol provided to the builder. Using the alphanumeric symbols...");
				useAlphaNumerics();
			}
			int pwdLength = length < 0 ? randomInt(DEFAULT_LENGTH) : length;
			return new RandomPasswordGenerator(pwdLength, symbols.toString().toCharArray());
		}
	}
}

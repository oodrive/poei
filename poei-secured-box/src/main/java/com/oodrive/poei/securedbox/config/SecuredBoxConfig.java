package com.oodrive.poei.securedbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.oodrive.poei.securedbox.dao.SecretDAO;
import com.oodrive.poei.securedbox.secret.SecretStore;
import com.oodrive.poei.securedbox.secret.hasher.Hasher;
import com.oodrive.poei.securedbox.secret.hasher.Sha256Hasher;
import com.oodrive.poei.securedbox.secret.operator.AESSecretCryptographicOperator;
import com.oodrive.poei.securedbox.secret.operator.SecretCryptographicOperator;
import com.oodrive.poei.securedbox.secret.operator.SecretKeyGenerator;
import com.oodrive.poei.securedbox.secret.operator.Simple32BytesAESSecretKeyGenerator;
import com.oodrive.poei.securedbox.secret.salt.RandomSaltGenerator;
import com.oodrive.poei.securedbox.secret.salt.SaltGenerator;


@Configuration
public class SecuredBoxConfig {

	@Bean
	SecretDAO secretDAO(JdbcTemplate jdbcTemplate) {
		return new SecretDAO(jdbcTemplate);
	}

	@Bean
	SecretCryptographicOperator encryptor(SecretKeyGenerator secretKeyGenerator) {
		return new AESSecretCryptographicOperator(secretKeyGenerator);
	}

	@Bean
	Hasher hasher() {
		return new Sha256Hasher();
	}

	@Bean
	SecretKeyGenerator secretKeyGenerator(Hasher hasher) {
		return new Simple32BytesAESSecretKeyGenerator(hasher);
	}

	@Bean
	SaltGenerator saltGenerator() {
		return new RandomSaltGenerator();
	}

	@Bean
	SecretStore secretRegistrar(SecretDAO secretDAO, SecretCryptographicOperator secretCryptographicOperator, SaltGenerator saltGenerator) {
		return new SecretStore(secretDAO, secretCryptographicOperator, saltGenerator);
	}
}

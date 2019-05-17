package com.oodrive.poei.securedbox.secret;

import org.springframework.transaction.annotation.Transactional;

import com.oodrive.poei.securedbox.dao.SecretDAO;
import com.oodrive.poei.securedbox.dao.SecretValue;
import com.oodrive.poei.securedbox.exception.NoSuchSecretException;
import com.oodrive.poei.securedbox.secret.operator.SecretCryptographicOperator;
import com.oodrive.poei.securedbox.secret.salt.SaltGenerator;
import com.oodrive.poei.securedbox.web.dto.WriteSecret;


public class SecretStore {

	private final SecretDAO secretDAO;

	private final SecretCryptographicOperator secretCryptographicOperator;
	private final SaltGenerator saltGenerator;

	public SecretStore(SecretDAO secretDAO,
			SecretCryptographicOperator secretCryptographicOperator,
			SaltGenerator saltGenerator) {
		this.secretDAO = secretDAO;
		this.secretCryptographicOperator = secretCryptographicOperator;
		this.saltGenerator = saltGenerator;
	}

	@Transactional
	public void register(WriteSecret secret) {
		byte[] salt = saltGenerator.generate();
		byte[] value = secretCryptographicOperator.encrypt(secret.getValue(), secret.getPassword(), salt);
		secret.obfuscatePassword();
		if (secretDAO.exists(secret.getKey())) {
			secretDAO.update(secret.getKey(), value, salt);
		} else {
			secretDAO.create(secret.getKey(), value, salt);
		}
	}

	@Transactional(readOnly = true)
	public String fetch(String key, char[] password) {
		SecretValue secretValue = secretDAO.getValue(key).orElseThrow(NoSuchSecretException::new);
		return secretCryptographicOperator.decrypt(secretValue.getValue(), password, secretValue.getSalt());
	}
}

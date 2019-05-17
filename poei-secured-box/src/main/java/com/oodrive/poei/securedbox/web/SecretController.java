package com.oodrive.poei.securedbox.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oodrive.poei.securedbox.secret.SecretStore;
import com.oodrive.poei.securedbox.web.dto.GetSecret;
import com.oodrive.poei.securedbox.web.dto.ReadSecret;
import com.oodrive.poei.securedbox.web.dto.WriteSecret;


@RestController
@RequestMapping(path = "/secret")
public class SecretController {

	private final SecretStore secretStore;

	@Autowired
	public SecretController(SecretStore secretStore) {this.secretStore = secretStore;}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void register(@RequestBody WriteSecret secret) {
		secretStore.register(secret);
	}

	@PostMapping(path = "/{key}")
	public ReadSecret getSecretValue(@PathVariable String key, @RequestBody GetSecret secret) {
		String value = secretStore.fetch(key, secret.getPassword());
		secret.obfuscatePassword();
		return new ReadSecret(value);
	}
}

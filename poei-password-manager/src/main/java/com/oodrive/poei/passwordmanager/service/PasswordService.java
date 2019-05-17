package com.oodrive.poei.passwordmanager.service;

import org.springframework.transaction.annotation.Transactional;

import com.oodrive.poei.passwordmanager.dao.PasswordDAO;
import com.oodrive.poei.passwordmanager.hasher.Hasher;


public class PasswordService {

	private final PasswordDAO passwordDAO;

	private final Hasher hasher;

	public PasswordService(PasswordDAO passwordDAO, Hasher hasher) {
		this.passwordDAO = passwordDAO;
		this.hasher = hasher;
	}

	@Transactional
	public void create(char[] password) {
		String hashedPassword = hasher.hash(new String(password));
		passwordDAO.create(hashedPassword);
	}

	@Transactional(readOnly = true)
	public boolean exists(char[] password) {
		String hashedPassword = hasher.hash(new String(password));
		return passwordDAO.exists(hashedPassword);
	}
}

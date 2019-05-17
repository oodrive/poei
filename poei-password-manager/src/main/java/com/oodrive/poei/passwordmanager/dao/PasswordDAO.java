package com.oodrive.poei.passwordmanager.dao;

import org.springframework.jdbc.core.JdbcTemplate;


public class PasswordDAO {

	private final JdbcTemplate jdbcTemplate;

	public PasswordDAO(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

	public void create(String hashedPassword) {
		jdbcTemplate.update("INSERT INTO password (value) VALUES (?)", hashedPassword);
	}

	public Boolean exists(String hashedPassword) {
		return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT 1 FROM password WHERE value = ?)",
				new Object[] { hashedPassword },
				Boolean.class);
	}
}

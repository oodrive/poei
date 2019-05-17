package com.oodrive.poei.securedbox.dao;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;


public class SecretDAO {

	private static final String SQL_EXISTS = "SELECT EXISTS (SELECT 1 FROM secret WHERE secret_key = ?)";

	private static final String SQL_INSERT =
			"INSERT INTO secret(secret_key, secret_value, salt) VALUES (?, ?, ?) RETURNING secret_id";

	private static final String SQL_UPDATE =
			"UPDATE secret SET secret_value = ?, salt = ? WHERE secret_key = ? RETURNING secret_id";

	private static final String SQL_FIND_VALUE = "SELECT secret_value, salt FROM secret WHERE secret_key = ?";

	private final JdbcTemplate jdbcTemplate;

	public SecretDAO(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

	public Boolean exists(String key) {
		return jdbcTemplate.queryForObject(SQL_EXISTS, Boolean.class, key);
	}

	public Integer create(String key, byte[] value, byte[] salt) {
		return jdbcTemplate.queryForObject(SQL_INSERT, Integer.class, key, value, salt);
	}

	public Integer update(String key, byte[] value, byte[] salt) {
		return jdbcTemplate.queryForObject(SQL_UPDATE, Integer.class, value, salt, key);
	}

	public Optional<SecretValue> getValue(String key) {
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_VALUE,
					(rs, rowNum) -> new SecretValue(rs.getBytes("secret_value"), rs.getBytes("salt")),
					key));
		} catch (EmptyResultDataAccessException ignored) {
			return Optional.empty();
		}
	}
}

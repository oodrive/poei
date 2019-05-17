package com.oodrive.poei.sample.ping.dao;

import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcTemplate;


public class PingDAO {

	private static final String SQL_INSERT = "INSERT INTO ping (date_time) VALUES (?)";

	private static final String SQL_COUNT = "SELECT COUNT(*) FROM ping";

	private final JdbcTemplate jdbcTemplate;

	public PingDAO(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

	public void insert() {
		jdbcTemplate.update(SQL_INSERT, LocalDateTime.now());
	}

	public Integer count() {
		return jdbcTemplate.queryForObject(SQL_COUNT, Integer.class);
	}
}

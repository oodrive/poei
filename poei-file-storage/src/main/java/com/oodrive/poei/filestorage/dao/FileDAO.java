package com.oodrive.poei.filestorage.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.oodrive.poei.filestorage.web.dto.FileDTO;


public class FileDAO {

	private static final String SQL_INSERT = "INSERT INTO file (name, path) VALUES (?,?) RETURNING file_id";

	private static final String SQL_FIND_ALL = "SELECT file_id, name, path FROM file ORDER BY name ASC";

	private static final String SQL_FIND_BY_ID = "SELECT file_id, name, path FROM file WHERE file_id = ?";

	private static final String SQL_FIND_BY_NAME = "SELECT file_id, name, path FROM file WHERE name = ?";

	private static final RowMapper<FileDTO> ROW_MAPPER =
			(rs, rowNum) -> new FileDTO(rs.getInt("file_id"), rs.getString("name"), rs.getString("path"));

	private final JdbcTemplate jdbcTemplate;

	public FileDAO(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

	public Integer save(String name, String path) {
		return jdbcTemplate.queryForObject(SQL_INSERT, Integer.class, name, path);
	}

	public List<FileDTO> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, ROW_MAPPER);
	}

	public Optional<FileDTO> findById(int fileId) {
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, ROW_MAPPER, fileId));
		} catch (EmptyResultDataAccessException ignored) {
			return Optional.empty();
		}
	}

	public Optional<FileDTO> findByName(String name) {
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_NAME, ROW_MAPPER, name));
		} catch (EmptyResultDataAccessException ignored) {
			return Optional.empty();
		}
	}
}


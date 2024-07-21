package com.handicraft.vernissage.port.adapters.persistence.handlers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface JdbcPostgresExecuterRepo {

    void update(String sqlTemplate, MapSqlParameterSource params);

    void delete(String tableName, String id);

    <T> List<T> all(String tableName, RowMapper<T> rowMapper);

    <T> List<T> customQuery(String sqlTemplate, MapSqlParameterSource params, RowMapper<T> rowMapper);

    <T> Optional<T> ofId(String tableName, String id, RowMapper<T> rowMapper);

    Boolean valueExists(String tableName, String colName, String value);

    void save(String table, List<String> columns, MapSqlParameterSource params);

}

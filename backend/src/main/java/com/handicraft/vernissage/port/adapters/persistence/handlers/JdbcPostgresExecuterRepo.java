package com.handicraft.vernissage.port.adapters.persistence.handlers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface JdbcPostgresExecuterRepo {

    void update(String sqlTemplate, MapSqlParameterSource params);

    void delete(String tableName, String id);

    <T> List<T> all(String tableName, RowMapper<T> rowMapper);

    <T> Optional<T> ofId(String tableName, String id, RowMapper<T> rowMapper);

    void save(List<String> columns, HashMap<String, Object> modelMap);

}

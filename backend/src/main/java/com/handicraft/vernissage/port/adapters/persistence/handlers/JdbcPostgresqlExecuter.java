package com.handicraft.vernissage.port.adapters.persistence.handlers;

import com.handicraft.vernissage.port.adapters.persistence.models.MasterSQLModel;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.handicraft.vernissage.port.adapters.persistence.models.MasterSQLModel.*;
import static com.handicraft.vernissage.port.adapters.persistence.models.MasterSQLModel.descriptionCol;

@Component
public class JdbcPostgresqlExecuter implements JdbcPostgresExecuterRepo {


    private final NamedParameterJdbcOperations jdbcOperations;

    public JdbcPostgresqlExecuter(DataSource dataSource) {
        this.jdbcOperations = new NamedParameterJdbcTemplate(dataSource);
    }

    private static String psqlExceptionMessage(DataAccessException e) {
        var exceptionMessage = "Unmapped DataAccessException";
        var exceptionSourceMessage = e.getMessage();

        if (e.getCause() instanceof PSQLException sqlException) {
            // нужно всегда сужать тип ошибки до PSQLException и до getServerErrorMessage и тд, и только в конце конкретно, как внизу
            if (sqlException.getServerErrorMessage() != null && sqlException.getServerErrorMessage().getMessage() != null) {
                var sqlMessage = sqlException.getServerErrorMessage().getMessage();
                if (sqlException.getServerErrorMessage().getMessage().contains("violates foreign key")) {
                    exceptionMessage = "FK violation: " + sqlMessage;
                }
                else if (sqlException.getServerErrorMessage().getMessage().contains("violates not-null constraint")) {
                    exceptionMessage = "NullNotAllowed: " + sqlMessage;
                }
                else if (sqlException.getServerErrorMessage().getMessage().contains("duplicate key value violates unique constraint")) {
                    exceptionMessage = "DuplicateKeyViolation: " + sqlMessage;
                }
                else if (sqlException.getServerErrorMessage().getMessage().contains("No value registered for")) {
                    exceptionMessage = "MissingParam: " + sqlMessage;
                }
            }
        } else if (exceptionSourceMessage.contains("No value registered for")) {
            exceptionMessage = "MissingParam: " + exceptionSourceMessage;
        }
        return exceptionMessage + ": " + exceptionSourceMessage;
    }


    public void update(String sqlTemplate, MapSqlParameterSource params) {
//        jdbcOperations.update(sqlTemplate, params);
        try {
            jdbcOperations.update(sqlTemplate, params);
        } catch (DataAccessException e) {
            throw new IllegalStateException(psqlExceptionMessage(e));
        }
    }

    public void delete(String tableName, String id){
        var sqlTemplate = String.format("""
                delete from %s 
                where id = :id""", tableName);

        var params = new MapSqlParameterSource()
                .addValue("id", id);

        update(sqlTemplate, params);
    }

    public <T> List<T> all(String tableName, RowMapper<T> rowMapper){
        var sqlTemplate = String.format("select * from %s", tableName);

        return jdbcOperations.query(sqlTemplate, new MapSqlParameterSource(), rowMapper);
    }

    @Override
    public <T> Optional<T> ofId(String tableName, String id, RowMapper<T> rowMapper) {
        var sqlTemplate = String.format("""
                select * from %s 
                where id = :id""", tableName);

        var params = new MapSqlParameterSource()
                .addValue("id", id);

        return jdbcOperations.query(sqlTemplate, params, rowMapper).stream().findFirst();
    }

    @Override
    public void save(List<String> columns, HashMap<String, Object> modelMap) {
        var joinedCols = String.join(", ", columns.stream().map((x) -> STR."\{x}").toList());
        var joinedParamCols = String.join(", ", columns.stream().map((x) -> STR.":\{x}").toList());

        var sqlTemplate = STR."""
            insert into \{table}
            (\{joinedCols})
            values
            (\{joinedParamCols})
        """;
        var params = new MapSqlParameterSource();

        for (Map.Entry<String, Object> entry: modelMap.entrySet()){
            params.addValue(entry.getKey(), entry.getValue());
        }

        update(sqlTemplate, params);
    }
}

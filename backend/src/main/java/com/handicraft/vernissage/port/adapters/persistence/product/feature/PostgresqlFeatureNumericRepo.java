package com.handicraft.vernissage.port.adapters.persistence.product.feature;

import com.handicraft.vernissage.domain.product.feature.*;
import com.handicraft.vernissage.port.adapters.persistence.handlers.JdbcPostgresExecuterRepo;
import com.handicraft.vernissage.port.adapters.persistence.models.FeatureNumericSQLModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.handicraft.vernissage.port.adapters.persistence.models.FeatureNumericSQLModel.*;


@Repository
public class PostgresqlFeatureNumericRepo implements FeatureNumericRepo {

    private final JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo;

    public PostgresqlFeatureNumericRepo(JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo) {
        this.jdbcPostgresExecuterRepo = jdbcPostgresExecuterRepo;
    }

    private static RowMapper<FeatureNumeric> asFeatureNumericRowMapping() {
        return (rs, _) -> new FeatureNumeric(
                rs.getString(idCol),
                rs.getDouble(fromValueCol),
                Optional.ofNullable(rs.getDouble(toValueCol) != 0.0 ? rs.getDouble(toValueCol) : null),
                rs.getString(unitCol),
                Optional.ofNullable(rs.getString(lessThanTextCol)),
                Optional.ofNullable(rs.getString(moreThanTextCol))
        );
    }

    private MapSqlParameterSource params(FeatureNumeric featureNumeric){
        var params = new MapSqlParameterSource();
        params.addValue(idCol, featureNumeric.id());
        params.addValue(fromValueCol, featureNumeric.from());
        params.addValue(toValueCol, featureNumeric.to().orElse(null));
        params.addValue(unitCol, featureNumeric.unit());
        params.addValue(lessThanTextCol, featureNumeric.lessThanText().orElse(null));
        params.addValue(moreThanTextCol, featureNumeric.moreThanText().orElse(null));
        return params;
    }

    @Override
    public void save(@NotNull FeatureNumeric featureNumeric) {
        jdbcPostgresExecuterRepo.save(table, FeatureNumericSQLModel.columns(), params(featureNumeric));
    }

    @Override
    public List<FeatureNumeric> all() {
        return jdbcPostgresExecuterRepo.all(table, asFeatureNumericRowMapping());
    }
}
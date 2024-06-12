package com.handicraft.vernissage.port.adapters.persistence.product.feature;

import com.handicraft.vernissage.domain.product.feature.*;
import com.handicraft.vernissage.port.adapters.persistence.handlers.JdbcPostgresExecuterRepo;
import com.handicraft.vernissage.port.adapters.persistence.models.FeatureTextSQLModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.handicraft.vernissage.port.adapters.persistence.models.FeatureTextSQLModel.*;


@Repository
public class PostgresqlFeatureTextRepo implements FeatureTextRepo {

    private final JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo;

    public PostgresqlFeatureTextRepo(JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo) {
        this.jdbcPostgresExecuterRepo = jdbcPostgresExecuterRepo;
    }

    private static RowMapper<FeatureText> asFeatureTextRowMapping() {
        return (rs, _) -> new FeatureText(
                rs.getString(idCol),
                rs.getString(valueCol)
        );
    }

    private MapSqlParameterSource params(FeatureText featureText){
        var params = new MapSqlParameterSource();
        params.addValue(idCol, featureText.id());
        params.addValue(valueCol, featureText.value());
        return params;
    }

    @Override
    public void save(@NotNull FeatureText featureText) {
        jdbcPostgresExecuterRepo.save(table, FeatureTextSQLModel.columns(), params(featureText));
    }

    @Override
    public List<FeatureText> all() {
        return jdbcPostgresExecuterRepo.all(table, asFeatureTextRowMapping());
    }

    @Override
    public Optional<FeatureText> ofId(String id) {
        return jdbcPostgresExecuterRepo.ofId(table, id, asFeatureTextRowMapping());
    }
}
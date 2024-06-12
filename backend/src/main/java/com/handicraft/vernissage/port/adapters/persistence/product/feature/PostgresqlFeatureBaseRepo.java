package com.handicraft.vernissage.port.adapters.persistence.product.feature;

import com.handicraft.vernissage.domain.product.feature.FeatureBase;
import com.handicraft.vernissage.domain.product.feature.FeatureBaseRepo;
import com.handicraft.vernissage.domain.product.feature.FeatureDiscriminator;
import com.handicraft.vernissage.port.adapters.persistence.handlers.JdbcPostgresExecuterRepo;
import com.handicraft.vernissage.port.adapters.persistence.models.FeatureBaseSQLModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.handicraft.vernissage.port.adapters.persistence.models.FeatureBaseSQLModel.*;


@Repository
public class PostgresqlFeatureBaseRepo implements FeatureBaseRepo {

    private final JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo;

    public PostgresqlFeatureBaseRepo(JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo) {
        this.jdbcPostgresExecuterRepo = jdbcPostgresExecuterRepo;
    }

    private static RowMapper<FeatureBase> asFeatureBaseRowMapping() {
        return (rs, _) -> new FeatureBase(
                rs.getString(idCol),
                rs.getString(nameCol),
                Optional.ofNullable(rs.getString(descriptionCol)),
                Optional.ofNullable(rs.getString(parentIdCol)),
                rs.getString(categoryIdCol),
                FeatureDiscriminator.valueOf(rs.getString(discriminatorCol))
        );
    }

    private MapSqlParameterSource params(FeatureBase featureBase){
        var params = new MapSqlParameterSource();
        params.addValue(idCol, featureBase.id());
        params.addValue(nameCol, featureBase.name());
        params.addValue(descriptionCol, featureBase.description().orElse(null));
        params.addValue(parentIdCol, featureBase.parentId().orElse(null));
        params.addValue(categoryIdCol, featureBase.categoryId());
        params.addValue(discriminatorCol, featureBase.discriminator().name());
        return params;
    }

    @Override
    public void save(@NotNull FeatureBase featureBase) {
        jdbcPostgresExecuterRepo.save(table, FeatureBaseSQLModel.columns(), params(featureBase));
    }

    @Override
    public List<FeatureBase> all() {
        return jdbcPostgresExecuterRepo.all(table, asFeatureBaseRowMapping());
    }
}
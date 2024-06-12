package com.handicraft.vernissage.port.adapters.persistence.product;

import com.handicraft.vernissage.domain.product.master.Master;
import com.handicraft.vernissage.domain.product.master.MasterRepo;
import com.handicraft.vernissage.port.adapters.persistence.handlers.JdbcPostgresExecuterRepo;
import com.handicraft.vernissage.port.adapters.persistence.models.MasterSQLModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.handicraft.vernissage.port.adapters.persistence.models.MasterSQLModel.*;


@Repository
public class PostgresqlMasterRepo implements MasterRepo {

    private final JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo;

    public PostgresqlMasterRepo(JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo) {
        this.jdbcPostgresExecuterRepo = jdbcPostgresExecuterRepo;
    }

    private static RowMapper<Master> asMasterRowMapping() {
        return (rs, _) -> new Master(
                rs.getString(idCol),
                rs.getString(nameCol),
                rs.getString(descriptionCol)
        );
    }

    private MapSqlParameterSource params(Master master){
        var params = new MapSqlParameterSource();
        params.addValue(idCol, master.id());
        params.addValue(nameCol, master.name());
        params.addValue(descriptionCol, master.description());
        return params;
    }

    @Override
    public void save(@NotNull Master master) {
        jdbcPostgresExecuterRepo.save(table, MasterSQLModel.columns(), params(master));
    }

    @Override
    public List<Master> all() {
        return jdbcPostgresExecuterRepo.all(table, asMasterRowMapping());
    }
}
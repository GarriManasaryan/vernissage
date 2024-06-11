package com.handicraft.vernissage.port.adapters.persistence;

import com.handicraft.vernissage.domain.product.category.ProductCategory;
import com.handicraft.vernissage.domain.product.category.ProductCategoryRepo;
import com.handicraft.vernissage.domain.product.master.Master;
import com.handicraft.vernissage.domain.product.master.MasterRepo;
import com.handicraft.vernissage.port.adapters.persistence.handlers.JdbcPostgresExecuterRepo;
import com.handicraft.vernissage.port.adapters.persistence.models.MasterSQLModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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

    private static HashMap<String, Object> modelMap(Master master){
        HashMap<String, Object> map = new HashMap<>();
        map.put(idCol, master.id());
        map.put(nameCol, master.name());
        map.put(descriptionCol, master.description());
        return map;

    }

    @Override
    public void save(@NotNull Master master) {
        jdbcPostgresExecuterRepo.save(MasterSQLModel.columns(), modelMap(master));
    }

    @Override
    public List<Master> all() {
        return jdbcPostgresExecuterRepo.all(table, asMasterRowMapping());
    }
}
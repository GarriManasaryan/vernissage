package com.handicraft.vernissage.port.adapters.persistence.product;

import com.handicraft.vernissage.domain.product.Product;
import com.handicraft.vernissage.domain.product.ProductRepo;
import com.handicraft.vernissage.port.adapters.persistence.handlers.JdbcPostgresExecuterRepo;
import com.handicraft.vernissage.port.adapters.persistence.models.ProductSQLModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.handicraft.vernissage.port.adapters.persistence.models.ProductSQLModel.*;


@Repository
public class PostgresqlProductRepo implements ProductRepo {

    private final JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo;

    public PostgresqlProductRepo(JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo) {
        this.jdbcPostgresExecuterRepo = jdbcPostgresExecuterRepo;
    }

    private static RowMapper<Product> asProductRowMapping() {
        return (rs, _) -> new Product(
                rs.getString(idCol),
                rs.getString(nameCol),
                Optional.ofNullable(rs.getString(descriptionCol)),
                rs.getString(priceIdCol),
                rs.getString(masterIdCol),
                rs.getString(categoryIdCol)
        );
    }

    private MapSqlParameterSource params(Product product){
        var params = new MapSqlParameterSource();
        params.addValue(idCol, product.id());
        params.addValue(nameCol, product.name());
        params.addValue(descriptionCol, product.description().orElse(null));
        params.addValue(priceIdCol, product.priceId());
        params.addValue(masterIdCol, product.masterId());
        params.addValue(categoryIdCol, product.categoryId());
        return params;
    }

    @Override
    public void save(@NotNull Product product) {
        jdbcPostgresExecuterRepo.save(table, ProductSQLModel.columns(), params(product));
    }

    @Override
    public List<Product> all() {
        return jdbcPostgresExecuterRepo.all(table, asProductRowMapping());
    }
}
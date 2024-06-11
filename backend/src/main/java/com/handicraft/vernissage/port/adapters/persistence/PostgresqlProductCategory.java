package com.handicraft.vernissage.port.adapters.persistence;

import com.handicraft.vernissage.domain.product.category.ProductCategory;
import com.handicraft.vernissage.domain.product.category.ProductCategoryRepo;
import com.handicraft.vernissage.port.adapters.persistence.handlers.JdbcPostgresExecuterRepo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.handicraft.vernissage.port.adapters.persistence.models.ProductCategorySQLModel.*;


@Repository
public class PostgresqlProductCategory implements ProductCategoryRepo {

    private final JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo;

    public PostgresqlProductCategory(JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo) {
        this.jdbcPostgresExecuterRepo = jdbcPostgresExecuterRepo;
    }

    private static RowMapper<ProductCategory> asProductCategoryRowMapping() {
        return (rs, rowNum) -> new ProductCategory(
                rs.getString(idCol),
                rs.getString(nameCol),
                rs.getString(descriptionCol),
                rs.getString(parentIdCol)
        );
    }

    @Override
    public void save(ProductCategory productCategory) {
        var sqlTemplate = STR."""
            insert into \{table}
            (\{idCol}, \{nameCol}, \{descriptionCol}, \{parentIdCol})
            values
            (:\{idCol}, :\{nameCol}, :\{descriptionCol}, :\{parentIdCol})
        """;
        var params = new MapSqlParameterSource()
                .addValue(idCol, productCategory.id())
                .addValue(nameCol, productCategory.name())
                .addValue(descriptionCol, productCategory.description())
                .addValue(parentIdCol, productCategory.parentId());

        jdbcPostgresExecuterRepo.update(sqlTemplate, params);

    }

    @Override
    public List<ProductCategory> all() {
        return jdbcPostgresExecuterRepo.all(table, asProductCategoryRowMapping());
    }
}
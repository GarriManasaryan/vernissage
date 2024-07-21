package com.handicraft.vernissage.port.adapters.persistence.product;

import com.handicraft.vernissage.domain.product.category.Category;
import com.handicraft.vernissage.domain.product.category.CategoryRepo;
import com.handicraft.vernissage.port.adapters.persistence.handlers.JdbcPostgresExecuterRepo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.handicraft.vernissage.port.adapters.persistence.models.CategorySQLModel.*;


@Repository
public class PostgresqlCategory implements CategoryRepo {

    private final JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo;

    public PostgresqlCategory(JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo) {
        this.jdbcPostgresExecuterRepo = jdbcPostgresExecuterRepo;
    }

    public static RowMapper<Category> asCategoryRowMapping() {
        return (rs, _) -> new Category(
                rs.getString(idCol),
                rs.getString(nameCol),
                rs.getString(descriptionCol),
                rs.getString(parentIdCol)
        );
    }

    @Override
    public void save(Category category) {
        var sqlTemplate = STR."""
            insert into \{table}
            (\{idCol}, \{nameCol}, \{descriptionCol}, \{parentIdCol})
            values
            (:\{idCol}, :\{nameCol}, :\{descriptionCol}, :\{parentIdCol})
        """;
        var params = new MapSqlParameterSource()
                .addValue(idCol, category.id())
                .addValue(nameCol, category.name())
                .addValue(descriptionCol, category.description())
                .addValue(parentIdCol, category.parentId());

        jdbcPostgresExecuterRepo.update(sqlTemplate, params);

    }

    @Override
    public List<Category> all() {
        return jdbcPostgresExecuterRepo.all(table, asCategoryRowMapping());
    }
}
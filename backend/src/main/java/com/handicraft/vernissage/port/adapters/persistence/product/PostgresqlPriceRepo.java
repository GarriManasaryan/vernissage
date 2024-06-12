package com.handicraft.vernissage.port.adapters.persistence.product;

import com.handicraft.vernissage.domain.product.price.Price;
import com.handicraft.vernissage.domain.product.price.PriceRepo;
import com.handicraft.vernissage.port.adapters.persistence.handlers.JdbcPostgresqlExecuter;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.handicraft.vernissage.port.adapters.persistence.models.PriceSQLModel.*;

@Repository
public class PostgresqlPriceRepo implements PriceRepo {

    private final JdbcPostgresqlExecuter jdbcPostgresqlExecuter;

    public PostgresqlPriceRepo(JdbcPostgresqlExecuter jdbcPostgresqlExecuter) {
        this.jdbcPostgresqlExecuter = jdbcPostgresqlExecuter;
    }

    private static RowMapper<Price> asPriceRowMapper() {
        return (rs, _) -> new Price(
                rs.getString(idCol),
                rs.getDouble(valueCol),
                rs.getString(currencyCol),
                // иначе вместо пустоты возвращает 0.0
                // да, в данном случае 0.0 даже удобнее, но чтобы с optional тренироваться оставим так
                Optional.ofNullable(rs.getDouble(discountCol) != 0.0 ? rs.getDouble(discountCol) : null)
        );
    }


    @Override
    public void save(@NotNull Price price) {
        var sqlTemplate = STR."""
                insert into \{table}
                (\{idCol}, \{valueCol}, \{currencyCol}, \{discountCol})
                values
                (:\{idCol}, :\{valueCol}, :\{currencyCol}, :\{discountCol})
                """;

        var params = new MapSqlParameterSource()
                .addValue(idCol, price.id())
                .addValue(valueCol, price.value())
                .addValue(currencyCol, price.currency())
                .addValue(discountCol, price.discount().orElse(null));

        jdbcPostgresqlExecuter.update(sqlTemplate, params);

    }

    @Override
    public List<Price> all() {
        return jdbcPostgresqlExecuter.all(table, asPriceRowMapper());
    }
}

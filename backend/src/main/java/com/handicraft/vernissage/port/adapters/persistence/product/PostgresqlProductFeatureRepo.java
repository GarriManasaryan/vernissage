//package com.handicraft.vernissage.port.adapters.persistence.product;
//
//import com.handicraft.vernissage.domain.product.DeprecatedProductFeature;
//import com.handicraft.vernissage.port.adapters.persistence.handlers.JdbcPostgresExecuterRepo;
//import com.handicraft.vernissage.port.adapters.persistence.models.ProductFeatureSQLModel;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//import static com.handicraft.vernissage.port.adapters.persistence.models.ProductFeatureSQLModel.*;
//
//
//@Repository
//public class PostgresqlProductFeatureRepo implements ProductFeatureRepo {
//
//    private final JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo;
//
//    public PostgresqlProductFeatureRepo(JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo) {
//        this.jdbcPostgresExecuterRepo = jdbcPostgresExecuterRepo;
//    }
//
//    private static RowMapper<DeprecatedProductFeature> asProductFeatureRowMapping() {
//        return (rs, _) -> new DeprecatedProductFeature(
//                rs.getString(productIdCol),
//                rs.getString(featureIdCol)
//        );
//    }
//
//    private MapSqlParameterSource params(DeprecatedProductFeature deprecatedProductFeature){
//        var params = new MapSqlParameterSource();
//        params.addValue(productIdCol, deprecatedProductFeature.productId());
//        params.addValue(featureIdCol, deprecatedProductFeature.featureId());
//        return params;
//    }
//
//    @Override
//    public void save(@NotNull DeprecatedProductFeature deprecatedProductFeature) {
//        jdbcPostgresExecuterRepo.save(table, ProductFeatureSQLModel.columns(), params(deprecatedProductFeature));
//    }
//
//    @Override
//    public List<DeprecatedProductFeature> all() {
//        return jdbcPostgresExecuterRepo.all(table, asProductFeatureRowMapping());
//    }
//
//    @Override
//    public List<DeprecatedProductFeature> getProductFeatures(@NotNull String productId) {
//        var sqlTemplate = STR."""
//                select * from \{table} where \{productIdCol} = :\{productIdCol}
//                """;
//
//        var params = new MapSqlParameterSource().addValue(productIdCol, productId);
//
//        return jdbcPostgresExecuterRepo.customQuery(sqlTemplate, params, asProductFeatureRowMapping());
//
//    }
//}
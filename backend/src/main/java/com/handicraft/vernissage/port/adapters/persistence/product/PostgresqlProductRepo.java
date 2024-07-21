package com.handicraft.vernissage.port.adapters.persistence.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.handicraft.vernissage.domain.product.Product;
import com.handicraft.vernissage.domain.product.ProductRepo;
import com.handicraft.vernissage.domain.product.category.Category;
import com.handicraft.vernissage.domain.product.feature.FeatureBase;
import com.handicraft.vernissage.domain.product.feature.FeatureDiscriminator;
import com.handicraft.vernissage.domain.product.feature.FeatureNumeric;
import com.handicraft.vernissage.domain.product.feature.FeatureText;
import com.handicraft.vernissage.domain.product.price.Price;
import com.handicraft.vernissage.domain.user.Master;
import com.handicraft.vernissage.port.adapters.persistence.handlers.JdbcPostgresExecuterRepo;
import com.handicraft.vernissage.port.adapters.persistence.handlers.JsonOperations;
import com.handicraft.vernissage.port.adapters.persistence.models.*;
import com.handicraft.vernissage.port.adapters.persistence.models.dto.ProductFeatureDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PostgresqlProductRepo implements ProductRepo {

    private final JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo;
    private final JsonOperations jsonOperations;

    public PostgresqlProductRepo(JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo, JsonOperations jsonOperations) {
        this.jdbcPostgresExecuterRepo = jdbcPostgresExecuterRepo;
        this.jsonOperations = jsonOperations;
    }

    private RowMapper<Product> asProductRowMapper() {
        return (rs, _) -> new Product(
                rs.getString(ProductSQLModel.idCol),
                rs.getString(ProductSQLModel.nameCol),
                Optional.ofNullable(rs.getString(ProductSQLModel.descriptionCol)),
                jsonOperations.asEntityRowMapper(rs.getString(ProductSQLModel.priceCol), Price.class),
                jsonOperations.asEntityRowMapper(rs.getString("user"), Master.class),
//                jsonOperations.asEntityListRowMapper(rs.getString("categories"), Category[].class),
                asCategoryRowMapper(rs.getString("categories")),
                asFeaturesListRowMapping(rs.getString("features"))
        );
    }

    private List<Category> asCategoryRowMapper(String categoriesJsonRow){
        var categories = new ArrayList<>(Arrays.asList(jsonOperations.asEntityRowMapper(categoriesJsonRow, Category[].class)));
        return categories.stream().filter(Objects::nonNull).toList();

    }

    private List<? extends FeatureBase> asFeaturesListRowMapping(String jsonString) {
        try {
            var objectMapper = jsonOperations.getObjectMapper();
            return objectMapper.readValue(jsonString, new TypeReference<List<ProductFeatureDTO>>() {})
                    .stream()
                    .filter(Objects::nonNull)
                    .map(productFeatureDTO -> switch (productFeatureDTO.discriminator()) {
                        case FeatureDiscriminator.FEATURE_NUMERIC -> new FeatureNumeric(
                                productFeatureDTO.id(),
                                productFeatureDTO.name(),
                                Optional.ofNullable(productFeatureDTO.description()),
                                Optional.ofNullable(productFeatureDTO.parentId()),
                                productFeatureDTO.categories().stream().filter(Objects::nonNull).toList(),
                                productFeatureDTO.from(),
                                Optional.ofNullable(productFeatureDTO.to()),
                                Optional.ofNullable(productFeatureDTO.unit()),
                                Optional.ofNullable(productFeatureDTO.lessThanText()),
                                Optional.ofNullable(productFeatureDTO.moreThanText())
                        );
                        case FeatureDiscriminator.FEATURE_TEXT -> new FeatureText(
                                productFeatureDTO.id(),
                                productFeatureDTO.name(),
                                Optional.ofNullable(productFeatureDTO.description()),
                                Optional.ofNullable(productFeatureDTO.parentId()),
                                productFeatureDTO.categories(),
                                productFeatureDTO.value()
                        );
                    })
                    .filter(Objects::nonNull)
                    .toList()
                    ;
        } catch (JsonProcessingException e){
            System.out.println(e.getMessage());
            return List.of();
        }

    }

    @Override
    public void save(Product product) {
        jdbcPostgresExecuterRepo.save(ProductSQLModel.table, ProductSQLModel.columns(), params(product));
    }

    private MapSqlParameterSource params(Product product) {
        var params = new MapSqlParameterSource();
        params.addValue(ProductSQLModel.idCol, product.id());
        params.addValue(ProductSQLModel.nameCol, product.name());
        params.addValue(ProductSQLModel.descriptionCol, product.description());
        params.addValue(ProductSQLModel.masterIdCol, product.master().id());
        params.addValue(ProductSQLModel.priceCol, jsonOperations.serializeFromObjToStringJson(product.price()));
        return params;
    }

    private String productQuery(){
        return """
                                    WITH categories AS (
                                        SELECT
                                            pr.id AS product_id,
                                            jsonb_agg(
                                                CASE\s
                                                    WHEN pr_category.category_id IS NOT NULL THEN
                                                        jsonb_build_object(                           \s
                                                            'id', category.id,
                                                            'name', category.name,
                                                            'description', category.description,
                                                            'parentId', category.parent_id
                                                        )
                                                    ELSE NULL
                                                END
                                            ) AS categories
                                        FROM
                                            vr_products pr
                                        LEFT JOIN
                                            vr_product_categories pr_category ON pr.id = pr_category.product_id
                                        LEFT JOIN
                                            vr_categories category ON pr_category.category_id = category.id
                                        GROUP BY
                                            pr.id
                                    ), feature_categories AS (
                                        SELECT
                                            feature.id,
                                            jsonb_agg(
                                                case when category.id is not null then
                                                    jsonb_build_object(                           \s
                                                        'id', category.id,
                                                        'name', category.name,
                                                        'description', category.description,
                                                        'parentId', category.parent_id
                                                    )
                                                else null\s
                                                end
                                            ) AS categories
                                        FROM
                                            vr_features feature
                                        LEFT JOIN
                                            vr_feature_categories ft_feature_category ON feature.id = ft_feature_category.feature_id
                                        LEFT JOIN
                                            vr_categories category ON ft_feature_category.category_id = category.id
                                        GROUP BY
                                            feature.id
                                    ), features AS (
                                        SELECT
                                            pr.id AS product_id,
                                            jsonb_agg(
                                                case
                                                    when pr_feature.feature_id is not null then
                                                        jsonb_build_object(
                                                            'id', feature_base."id",
                                                            'name', feature_base."name",
                                                            'description', feature_base."description",
                                                            'parentId', feature_base.parent_id,
                                                            'value', ft_text."value",
                                                            'from', ft_numeric.from_value,
                                                            'to', ft_numeric.to_value,
                                                            'unit', ft_numeric.unit,
                                                            'categories', feature_categories.categories,
                                                            'lessThanText', ft_numeric.less_than_text,
                                                            'moreThanText', ft_numeric.more_than_text,
                                                            'discriminator', feature_base.discriminator
                                                        )
                                                    else null\s
                                                end
                                            ) AS features
                                        FROM
                                            vr_products pr
                                        LEFT JOIN
                                            vr_product_features pr_feature ON pr.id = pr_feature.product_id
                                        LEFT JOIN
                                            vr_features feature_base ON pr_feature.feature_id = feature_base.id
                                        LEFT JOIN
                                            vr_features_text ft_text ON pr_feature.feature_id = ft_text.id
                                        LEFT JOIN
                                            vr_features_numeric ft_numeric ON pr_feature.feature_id = ft_numeric.id
                                        LEFT JOIN
                                            feature_categories ON pr_feature.feature_id = feature_categories.id
                                        GROUP BY
                                            pr.id
                                    )
                --                    , features_numeric AS (
                --                        SELECT
                --                            pr.id AS product_id,
                --                            jsonb_agg(
                --                                case
                --                                    when pr_feature.feature_id is not null and feature_base.discriminator = 'FEATURE_NUMERIC' then
                --                                        jsonb_build_object(
                --                                            'id', feature_base."id",
                --                                            'name', feature_base."name",
                --                                            'description', feature_base."description",
                --                                            'parentId', feature_base.parent_id,
                --                                            'categoryId', feature_base.category_id,
                --                                            'from', ft_numeric.from_value,
                --                                            'to', ft_numeric.to_value,
                --                                            'unit', ft_numeric.unit,
                --                                            'lessThanText', ft_numeric.less_than_text,
                --                                            'moreThanText', ft_numeric.more_than_text,
                --                                            'discriminator', feature_base.discriminator
                --                                        )
                --                                    else null\s
                --                                end
                --                            ) AS features
                --                        FROM
                --                            vr_products pr
                --                        LEFT JOIN
                --                            vr_product_features pr_feature ON pr.id = pr_feature.product_id
                --                        LEFT JOIN
                --                            vr_features feature_base ON pr_feature.feature_id = feature_base.id
                --                        LEFT JOIN
                --                            vr_features_numeric ft_numeric ON pr_feature.feature_id = ft_numeric.id
                --                        GROUP BY
                --                            pr.id
                --                    )
                                    SELECT
                                        pr.*,
                                        jsonb_build_object(
                                            'id', usr.id,
                                            'name', usr.name,
                                            'role', usr.role,
                                            'timeZone', usr.time_zone
                                        ) as user,
                                        categories,
                --                        jsonb_strip_nulls - если есть поля у объекта с налл, убирает эти поля
                --                        jsonb_strip_nulls(features_text.features) as features_text,
                --                        jsonb_strip_nulls(features_numeric.features) as features_numeric
                --                        case when jsonb_array_length(features.features) = 0 then null else features.features end as features
                                        features.features as features
                                    FROM
                                        vr_products pr
                                    LEFT JOIN
                                        categories ON pr.id = categories.product_id
                                    LEFT JOIN
                                        vr_users usr ON pr.master_id = usr.id
                --                    LEFT JOIN
                --                        features_text ON pr.id = features_text.product_id
                --                    LEFT JOIN
                --                        features_numeric ON pr.id = features_numeric.product_id
                                    LEFT JOIN
                                        features ON pr.id = features.product_id
                                        ;
            """;
    }

    @Override
    public List<Product> all() {
        return jdbcPostgresExecuterRepo.customQuery(productQuery(), new MapSqlParameterSource(), asProductRowMapper());
    }
}
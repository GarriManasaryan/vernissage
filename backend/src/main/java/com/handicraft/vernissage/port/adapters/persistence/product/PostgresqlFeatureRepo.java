package com.handicraft.vernissage.port.adapters.persistence.product;

import com.handicraft.vernissage.domain.product.Product;
import com.handicraft.vernissage.domain.product.category.Category;
import com.handicraft.vernissage.domain.product.feature.*;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.requests.CategoryFeatureCreationModel;
import com.handicraft.vernissage.port.adapters.persistence.handlers.JdbcPostgresExecuterRepo;
import com.handicraft.vernissage.port.adapters.persistence.models.*;
import com.handicraft.vernissage.port.adapters.persistence.models.dto.FeatureDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PostgresqlFeatureRepo implements FeatureRepo {

    private final JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo;

    public PostgresqlFeatureRepo(JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo) {
        this.jdbcPostgresExecuterRepo = jdbcPostgresExecuterRepo;
    }


    private RowMapper<FeatureDTO> asFeatureDTORowMapper() {
        return (rs, _) -> new FeatureDTO(
                rs.getString(FeatureBaseSQLModel.idCol),
                rs.getString(FeatureBaseSQLModel.nameCol),
                rs.getString(FeatureBaseSQLModel.descriptionCol),
                rs.getString(FeatureBaseSQLModel.parentIdCol),
                FeatureDiscriminator.valueOf(rs.getString(FeatureBaseSQLModel.discriminatorCol)),
                rs.getDouble(FeatureNumericSQLModel.fromValueCol),
                rs.getDouble(FeatureNumericSQLModel.toValueCol),
                rs.getString(FeatureNumericSQLModel.unitCol),
                rs.getString(FeatureNumericSQLModel.lessThanTextCol),
                rs.getString(FeatureNumericSQLModel.moreThanTextCol),
                rs.getString(FeatureTextSQLModel.valueCol),
                rs.getString("categoryId"),
                rs.getString("categoryName"),
                rs.getString("categoryDescription"),
                rs.getString("categoryParentId")
        );
    }

    private List<? extends FeatureBase> regroupFeatureDTO(List<FeatureDTO> featuresDTO) {
        return featuresDTO.stream()
                .collect(Collectors.groupingBy(FeatureDTO::id))
                .values().stream()
                // словарь ид: список груп FeatureDTO,
                // итерируемся по values - собираем отдельно категории, а остальные данные одинаковые, getFirst()
                .map(featureDTOsGroupedById -> {
                    var categories = featureDTOsGroupedById.stream().map(
                            featureDTO -> featureDTO.categoryId() != null && featureDTO.categoryName() != null ? new Category(
                                    featureDTO.categoryId(),
                                    featureDTO.categoryName(),
                                    featureDTO.categoryDescription(),
                                    featureDTO.categoryParentId()
                            ) : null
                    ).filter(Objects::nonNull).toList();
                    var featureDTOBase = featureDTOsGroupedById.getFirst();
                    return switch (featureDTOBase.discriminator()) {
                        case FeatureDiscriminator.FEATURE_NUMERIC -> new FeatureNumeric(
                                featureDTOBase.id(),
                                featureDTOBase.name(),
                                Optional.ofNullable(featureDTOBase.description()),
                                Optional.ofNullable(featureDTOBase.parentId()),
                                categories,
                                featureDTOsGroupedById.getFirst().from(),
                                Optional.ofNullable(featureDTOBase.to()),
                                Optional.ofNullable(featureDTOBase.unit()),
                                Optional.ofNullable(featureDTOBase.lessThanText()),
                                Optional.ofNullable(featureDTOBase.moreThanText())
                        );
                        case FeatureDiscriminator.FEATURE_TEXT -> new FeatureText(
                                featureDTOBase.id(),
                                featureDTOBase.name(),
                                Optional.ofNullable(featureDTOBase.description()),
                                Optional.ofNullable(featureDTOBase.parentId()),
                                categories,
                                featureDTOBase.value()
                        );
                    };
                })
                .toList()
                ;

    }


    private String featureQuery() {
        return """
                    select\s
                    features.id as id,
                    features.name as name,
                    features.description as description,
                    features.parent_id,
                    features.discriminator,
                    feature_numeric.from_value,
                    feature_numeric.to_value,
                    feature_numeric.unit,
                    feature_numeric.less_than_text,
                    feature_numeric.more_than_text,
                    feature_text."value",
                    category."id" as "categoryId",
                    category."name" as "categoryName",
                    category."description" as "categoryDescription",
                    category."parent_id" as "categoryParentId"
                    from vr_features features
                    left join\s
                    vr_feature_categories feature_category on features."id" = feature_category.feature_id
                    left join\s
                    vr_features_numeric feature_numeric on features."id" = feature_numeric.id
                    left join\s
                    vr_features_text feature_text on features."id" = feature_text.id
                    left join\s
                    vr_categories category on feature_category.category_id = category.id
                    ;
                """;
    }

    @Override
    public List<? extends FeatureBase> all() {
        var featuresDTO = jdbcPostgresExecuterRepo.customQuery(featureQuery(), new MapSqlParameterSource(), asFeatureDTORowMapper());
        return regroupFeatureDTO(featuresDTO);
    }

    private static RowMapper<Integer> asValueExistsMapper(){
        return (rs, _) -> rs.getInt(1);
    }

    @Override
    public Boolean nameAlreadyExists(@NotNull String name) {
        var params = new MapSqlParameterSource().addValue(FeatureBaseSQLModel.nameCol, name);
        String sqlTemplate = STR."SELECT 1 FROM \{FeatureBaseSQLModel.table} WHERE \{FeatureBaseSQLModel.nameCol} = :\{FeatureBaseSQLModel.nameCol}";
        return jdbcPostgresExecuterRepo.customQuery(
                sqlTemplate, params, asValueExistsMapper()
        ).stream().findFirst().orElse(null) != null;

    }

    private static MapSqlParameterSource saveFeatureBaseParams(FeatureBase featureBase) {
        var params = new MapSqlParameterSource();
        params.addValue(FeatureBaseSQLModel.idCol, featureBase.id());
        params.addValue(FeatureBaseSQLModel.nameCol, featureBase.name());
        params.addValue(FeatureBaseSQLModel.descriptionCol, featureBase.description().orElse(null));
        params.addValue(FeatureBaseSQLModel.discriminatorCol, featureBase.discriminator().name());
        params.addValue(FeatureBaseSQLModel.parentIdCol, featureBase.parentId().orElse(null));
        return params;
    }

    @Override
    public void saveFeatureBase(FeatureBase featureBase) {
        jdbcPostgresExecuterRepo.save(FeatureBaseSQLModel.table, FeatureBaseSQLModel.columns(), saveFeatureBaseParams(featureBase));
    }

    private static MapSqlParameterSource saveFeatureNumericParams(FeatureNumeric featureNumeric) {
        var params = new MapSqlParameterSource();
        params.addValue(FeatureNumericSQLModel.idCol, featureNumeric.id());
        params.addValue(FeatureNumericSQLModel.fromValueCol, featureNumeric.from());
        params.addValue(FeatureNumericSQLModel.toValueCol, featureNumeric.to().orElse(null));
        params.addValue(FeatureNumericSQLModel.unitCol, featureNumeric.unit().orElse(null));
        params.addValue(FeatureNumericSQLModel.lessThanTextCol, featureNumeric.lessThanText().orElse(null));
        params.addValue(FeatureNumericSQLModel.moreThanTextCol, featureNumeric.moreThanText().orElse(null));
        return params;
    }

    @Override
    public void saveFeatureNumeric(FeatureNumeric featureNumeric) {
        jdbcPostgresExecuterRepo.save(FeatureNumericSQLModel.table, FeatureNumericSQLModel.columns(), saveFeatureNumericParams(featureNumeric));
    }

    private static MapSqlParameterSource saveFeatureTextParams(FeatureText featureText) {
        var params = new MapSqlParameterSource();
        params.addValue(FeatureTextSQLModel.idCol, featureText.id());
        params.addValue(FeatureTextSQLModel.valueCol, featureText.value());
        return params;
    }

    @Override
    public void saveFeatureText(FeatureText featureText) {
        jdbcPostgresExecuterRepo.save(FeatureTextSQLModel.table, FeatureTextSQLModel.columns(), saveFeatureTextParams(featureText));

    }

    @Override
    public void addCategoriesToFeature(FeatureBase featureBase) {
        var sqlTemplateBase = STR."""
                insert into \{FeatureCategoriesSQLModel.table}
                (\{FeatureCategoriesSQLModel.featureIdCol}, \{FeatureCategoriesSQLModel.categoryIdCol})
                values
                """;

        List<String> values = new ArrayList<>();
        featureBase.categories().forEach(
                category -> values.add(STR."('\{featureBase.id()}', '\{category.id()}')")
        );

        sqlTemplateBase = STR."\{sqlTemplateBase} \{String.join(", ", values)}";

        jdbcPostgresExecuterRepo.update(sqlTemplateBase, new MapSqlParameterSource());

    }
}

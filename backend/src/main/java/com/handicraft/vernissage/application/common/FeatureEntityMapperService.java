package com.handicraft.vernissage.application.common;

import com.handicraft.vernissage.domain.product.category.Category;
import com.handicraft.vernissage.domain.product.feature.FeatureBase;
import com.handicraft.vernissage.domain.product.feature.FeatureNumeric;
import com.handicraft.vernissage.domain.product.feature.FeatureText;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.CategoryBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.backoffice.FeatureBaseBackofficeModelInterface;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.backoffice.FeatureNumericBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.backoffice.FeatureTextBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.requests.CategoryFeatureCreationModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Service
public class FeatureEntityMapperService {

    public static List<? extends FeatureBaseBackofficeModelInterface> switchMapFeaturesToBackofficeModels(List<? extends FeatureBase> features) {
        return features.stream().map(
                feature -> switch (feature) {
                    case FeatureNumeric featureNumeric -> new FeatureNumericBackofficeModel(
                            featureNumeric.id(),
                            featureNumeric.name(),
                            featureNumeric.description().orElse(null),
                            featureNumeric.parentId().orElse(null),
                            categoryBackofficeModels(featureNumeric.categories()),
                            featureNumeric.from(),
                            featureNumeric.to().orElse(null),
                            featureNumeric.unit().orElse(null),
                            featureNumeric.lessThanText().orElse(null),
                            featureNumeric.moreThanText().orElse(null)
                    );
                    case FeatureText featureText -> new FeatureTextBackofficeModel(
                            featureText.id(),
                            featureText.name(),
                            featureText.description().orElse(null),
                            featureText.parentId().orElse(null),
                            categoryBackofficeModels(featureText.categories()),
                            featureText.value()
                    );
                }
        ).toList();
    }


    public static List<CategoryBackofficeModel> categoryBackofficeModels(List<Category> categories) {
        return categories.stream().filter(Objects::nonNull).map(
                i -> new CategoryBackofficeModel(
                        i.id(),
                        i.name(),
                        i.description(),
                        i.parentId()
                )
        ).toList();

    }

    public static List<Category> categoryCreationRequestModels(List<CategoryFeatureCreationModel> categories) {
        return categories.stream().filter(Objects::nonNull).map(
                i -> new Category(
                        i.id(),
                        i.name(),
                        i.description(),
                        i.parentId()
                )
        ).toList();

    }

    public static Function<Category, CategoryBackofficeModel> toBackofficeModel = category ->
            new CategoryBackofficeModel(
                    category.id(),
                    category.name(),
                    category.description(),
                    category.parentId()
            );

    public static Function<CategoryBackofficeModel, Category> toCategory = model ->
            new Category(
                    model.id(),
                    model.name(),
                    model.description(),
                    model.parentId()
            );

    public static <S, T> List<T> mapCategoriesList(List<S> source, Function<S, T> mapperFunction) {
        return source.stream()
                .filter(Objects::nonNull)
                .map(mapperFunction)
                .toList();
    }


}

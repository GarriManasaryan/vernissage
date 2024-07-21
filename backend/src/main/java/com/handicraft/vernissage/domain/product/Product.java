package com.handicraft.vernissage.domain.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.handicraft.vernissage.application.IdGenerator;
import com.handicraft.vernissage.domain.product.category.Category;
import com.handicraft.vernissage.domain.product.feature.FeatureBase;
import com.handicraft.vernissage.domain.product.price.Price;
import com.handicraft.vernissage.domain.user.Master;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public record Product(

        @NotNull String id,
        @NotNull String name,
        @NotNull Optional<String> description,
        // price отдельно от продукта не может обновиться, поэтому можно объект вставить
        @NotNull Price price,
        // а вот у мастера свой жизненный цикл, так что по ид, чтобы подтянуть из базы самые свежие данные
        @NotNull Master master,
        // категории могут независимо добавляться и меняться, поэтому id
        @NotNull List<Category> categories,
        // аналогично
        @NotNull List<? extends FeatureBase> features

        ) {

    public static Product of(
            @NotNull String name,
            @Nullable String description,
            @NotNull Price price,
            @NotNull Master master,
            // категории могут независимо добавляться и меняться, поэтому id
            @NotNull List<Category> categories,
            // аналогично
            @NotNull List<? extends FeatureBase> features
    ){
        return new Product(
                IdGenerator.generate("prd"),
                name,
                Optional.ofNullable(description),
                price,
                master,
                categories,
                features
        );
    }

}

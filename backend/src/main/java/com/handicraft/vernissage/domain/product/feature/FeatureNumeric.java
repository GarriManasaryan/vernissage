package com.handicraft.vernissage.domain.product.feature;

import com.handicraft.vernissage.application.IdGenerator;
import com.handicraft.vernissage.domain.product.category.Category;
import com.handicraft.vernissage.domain.user.Role;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public record FeatureNumeric(
        @NotNull String id,
        @NotNull String name,
        @NotNull Optional<String> description,
        @NotNull Optional<String> parentId,
        @NotNull List<Category> categories,
        @NotNull Double from,
        @NotNull Optional<Double> to,
        @NotNull Optional<String> unit,
        @NotNull Optional<String> lessThanText,
        @NotNull Optional<String> moreThanText
) implements FeatureBase {

    @Override
    public @NotNull FeatureDiscriminator discriminator() {
        return FeatureDiscriminator.FEATURE_NUMERIC;
    }

    public static FeatureNumeric of(
            @NotNull String name,
            @Nullable String description,
            @Nullable String parentId,
            @NotNull List<Category> categories,
            @NotNull Double from,
            @Nullable Double to,
            @Nullable String unit,
            @Nullable String lessThanText,
            @Nullable String moreThanText
    ){
        return new FeatureNumeric(
                IdGenerator.generate("ftn"),
                name,
                Optional.ofNullable(description),
                Optional.ofNullable(parentId),
                categories,
                from,
                Optional.ofNullable(to),
                Optional.ofNullable(unit),
                Optional.ofNullable(lessThanText),
                Optional.ofNullable(moreThanText)
        );
    }

}

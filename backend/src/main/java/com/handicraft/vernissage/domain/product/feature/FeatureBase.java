package com.handicraft.vernissage.domain.product.feature;

import com.handicraft.vernissage.application.IdGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.lang.NonNullApi;

import java.util.Optional;

public record FeatureBase(
        @NotNull String id,
        @NotNull String name,
        @NotNull Optional<String> description,
        @NotNull Optional<String> parentId,
        @NotNull String categoryId,
        @NotNull FeatureDiscriminator discriminator
) {

    public static FeatureBase of(
            @NotNull String name,
            @Nullable String description,
            @Nullable String parentId,
            @NotNull String categoryId,
            @NotNull FeatureDiscriminator discriminator
    ){
        return new FeatureBase(
                IdGenerator.generate("ftb"),
                name,
                Optional.ofNullable(description),
                Optional.ofNullable(parentId),
                categoryId,
                discriminator
        );
    }

}

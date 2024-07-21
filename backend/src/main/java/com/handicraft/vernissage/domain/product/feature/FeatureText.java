package com.handicraft.vernissage.domain.product.feature;

import com.handicraft.vernissage.application.IdGenerator;
import com.handicraft.vernissage.domain.product.category.Category;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public record FeatureText(
        @NotNull String id,
        @NotNull String name,
        @NotNull Optional<String> description,
        @NotNull Optional<String> parentId,
        @NotNull List<Category> categories,
        @NotNull String value
) implements FeatureBase {

//    public FeatureText {
//        if (discriminator != FeatureDiscriminator.FEATURE_TEXT) {
//            throw new IllegalArgumentException("Discriminator should be FEATURE_TEXT");
//        }
//    }

    @Override
    public @NotNull FeatureDiscriminator discriminator() {
        return FeatureDiscriminator.FEATURE_TEXT;
    }

    public static FeatureText of(
            @NotNull String name,
            @Nullable String description,
            @Nullable String parentId,
            @NotNull List<Category> categories,
            @NotNull String value

    ){
        return new FeatureText(
                IdGenerator.generate("ftn"),
                name,
                Optional.ofNullable(description),
                Optional.ofNullable(parentId),
                categories,
                value
        );
    }

}

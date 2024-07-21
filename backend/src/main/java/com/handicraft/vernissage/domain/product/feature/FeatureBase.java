package com.handicraft.vernissage.domain.product.feature;

import com.handicraft.vernissage.domain.product.category.Category;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public sealed interface FeatureBase permits FeatureNumeric, FeatureText {
    @NotNull String id();
    @NotNull String name();
    @NotNull Optional<String> description();
    @NotNull Optional<String> parentId();
    @NotNull List<Category> categories();
    @NotNull FeatureDiscriminator discriminator();
}

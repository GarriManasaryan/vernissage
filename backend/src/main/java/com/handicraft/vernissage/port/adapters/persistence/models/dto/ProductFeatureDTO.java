package com.handicraft.vernissage.port.adapters.persistence.models.dto;

import com.handicraft.vernissage.domain.product.category.Category;
import com.handicraft.vernissage.domain.product.feature.FeatureDiscriminator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record ProductFeatureDTO(
        @NotNull String id,
        @NotNull String name,
        @Nullable String description,
        @Nullable String parentId,
        @NotNull List<Category> categories,
        @NotNull Double from,
        @Nullable Double to,
        @Nullable String unit,
        @Nullable String lessThanText,
        @Nullable String moreThanText,
        @NotNull String value,
        @NotNull FeatureDiscriminator discriminator
) {
}

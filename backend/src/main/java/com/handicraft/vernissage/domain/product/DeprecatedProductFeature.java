package com.handicraft.vernissage.domain.product;

import org.jetbrains.annotations.NotNull;

public record DeprecatedProductFeature(
        @NotNull String productId,
        @NotNull String featureId
) {
}

package com.handicraft.vernissage.port.adapters.backoffice.models.product;

import org.jetbrains.annotations.NotNull;

public record ProductFeatureCreationRequest(
        @NotNull String productId,
        @NotNull String featureId
) {
}

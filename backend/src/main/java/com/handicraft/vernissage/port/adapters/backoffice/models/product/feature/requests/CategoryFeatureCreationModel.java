package com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.requests;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record CategoryFeatureCreationModel(
        @NotNull String id,
        @NotNull String name,
        @Nullable String description,
        @Nullable String parentId
) {

}

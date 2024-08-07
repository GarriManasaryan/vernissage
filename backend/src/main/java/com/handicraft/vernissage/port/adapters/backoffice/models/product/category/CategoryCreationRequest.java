package com.handicraft.vernissage.port.adapters.backoffice.models.product.category;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record CategoryCreationRequest(
        @NotNull String name,
        @Nullable String description,
        @Nullable String parentId
) {

}

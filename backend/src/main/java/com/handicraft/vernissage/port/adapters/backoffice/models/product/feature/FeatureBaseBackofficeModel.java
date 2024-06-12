package com.handicraft.vernissage.port.adapters.backoffice.models.product.feature;

import com.handicraft.vernissage.application.IdGenerator;
import com.handicraft.vernissage.domain.product.feature.FeatureDiscriminator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record FeatureBaseBackofficeModel(
        @NotNull String id,
        @NotNull String name,
        @NotNull Optional<String> description,
        @NotNull Optional<String> parentId,
        @NotNull String categoryId,
        @NotNull FeatureDiscriminator discriminator
) {


}

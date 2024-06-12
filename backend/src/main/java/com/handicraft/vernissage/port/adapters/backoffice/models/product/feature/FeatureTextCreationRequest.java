package com.handicraft.vernissage.port.adapters.backoffice.models.product.feature;

import com.handicraft.vernissage.domain.product.feature.FeatureDiscriminator;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public record FeatureTextCreationRequest(
        @NotNull String name,
        @NotNull Optional<String> description,
        @NotNull Optional<String> parentId,
        @NotNull String categoryId,
        @NotNull String discriminator,
        @NotNull String value
) implements FeatureBaseInterface {

}

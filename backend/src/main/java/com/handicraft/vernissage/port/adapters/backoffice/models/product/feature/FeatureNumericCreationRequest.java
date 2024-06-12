package com.handicraft.vernissage.port.adapters.backoffice.models.product.feature;

import com.handicraft.vernissage.domain.product.feature.FeatureDiscriminator;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public record FeatureNumericCreationRequest(
        @NotNull String name,
        @NotNull Optional<String> description,
        @NotNull Optional<String> parentId,
        @NotNull String categoryId,
        @NotNull String discriminator,
        @NotNull Double from,
        @NotNull Optional<Double> to,
        @NotNull String unit,
        @NotNull Optional<String> lessThanText,
        @NotNull Optional<String> moreThanText
) implements FeatureBaseInterface {

}

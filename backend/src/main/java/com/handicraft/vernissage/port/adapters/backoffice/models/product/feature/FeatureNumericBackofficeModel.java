package com.handicraft.vernissage.port.adapters.backoffice.models.product.feature;

import com.handicraft.vernissage.application.IdGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record FeatureNumericBackofficeModel(
        @NotNull String id,
//        @NotNull String name,
//        @NotNull Optional<String> description,
//        @NotNull Optional<String> parentId,
//        @NotNull String categoryId,
//        @NotNull String discriminator,
        @NotNull Double from,
        @NotNull Optional<Double> to,
        @NotNull String unit,
        @NotNull Optional<String> lessThanText,
        @NotNull Optional<String> moreThanText
)   { // implements FeatureBaseInterface

}

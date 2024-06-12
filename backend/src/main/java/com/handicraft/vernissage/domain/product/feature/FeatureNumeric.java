package com.handicraft.vernissage.domain.product.feature;

import com.handicraft.vernissage.application.IdGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record FeatureNumeric(
        @NotNull String id,
        @NotNull Double from,
        @NotNull Optional<Double> to,
        @NotNull String unit,
        @NotNull Optional<String> lessThanText,
        @NotNull Optional<String> moreThanText
)   {

}

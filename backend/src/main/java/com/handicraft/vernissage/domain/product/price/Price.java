package com.handicraft.vernissage.domain.product.price;

import com.handicraft.vernissage.application.IdGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record Price(
        @NotNull Double value,
        @NotNull String currency,
        @NotNull Optional<Double> discount
) {

}

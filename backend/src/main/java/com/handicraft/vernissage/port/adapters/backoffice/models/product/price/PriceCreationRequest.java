package com.handicraft.vernissage.port.adapters.backoffice.models.product.price;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record PriceCreationRequest(
        @NotNull Double value,
        @NotNull String currency,
        @NotNull Optional<Double> discount
) {

}

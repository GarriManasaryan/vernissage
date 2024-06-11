package com.handicraft.vernissage.domain.product.price;

import com.handicraft.vernissage.application.IdGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record Price(
        @NotNull String id,
        @NotNull Double value,
        @NotNull String currency,
        @NotNull Optional<Double> discount
) {

    public static Price of(
            @NotNull Double value,
            @NotNull String currency,
            @Nullable Double discount
    ){
        return new Price(
                IdGenerator.generate("prc"),
                value,
                currency,
                Optional.ofNullable(discount)
        );
    }

}

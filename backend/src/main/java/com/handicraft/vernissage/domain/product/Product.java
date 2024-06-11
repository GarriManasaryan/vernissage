package com.handicraft.vernissage.domain.product;

import com.handicraft.vernissage.application.IdGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record Product(
        @NotNull String id,
        @NotNull String name,
        @NotNull Optional<String> description,
        @NotNull String priceId,
        @NotNull String masterId,
        @NotNull String categoryId

) {

    public static Product of(
            @NotNull String name,
            @Nullable String description,
            @NotNull String priceId,
            @NotNull String masterId,
            @NotNull String categoryId
    ){
        return new Product(
                IdGenerator.generate("prd"),
                name,
                Optional.ofNullable(description),
                priceId,
                masterId,
                categoryId
        );
    }

}

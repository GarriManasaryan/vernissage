package com.handicraft.vernissage.port.adapters.backoffice.models.product;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public record ProductCreationRequest(
        @NotNull String name,
        @NotNull Optional<String> description,
        @NotNull String priceId,
        @NotNull String masterId,
        @NotNull String categoryId

) {

}

package com.handicraft.vernissage.port.adapters.backoffice.models.product;

import com.handicraft.vernissage.application.IdGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record ProductBackofficeModel(
        @NotNull String id,
        @NotNull String name,
        @NotNull Optional<String> description,
        @NotNull String priceId,
        @NotNull String masterId,
        @NotNull String categoryId

) {

}

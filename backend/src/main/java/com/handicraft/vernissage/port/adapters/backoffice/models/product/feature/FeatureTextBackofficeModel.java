package com.handicraft.vernissage.port.adapters.backoffice.models.product.feature;

import com.handicraft.vernissage.application.IdGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public record FeatureTextBackofficeModel(
        @NotNull String id,
//        @NotNull String name,
//        @NotNull Optional<String> description,
//        @NotNull Optional<String> parentId,
//        @NotNull String categoryId,
//        @NotNull String discriminator,
        @NotNull String value
)  { // implements FeatureBaseInterface


}

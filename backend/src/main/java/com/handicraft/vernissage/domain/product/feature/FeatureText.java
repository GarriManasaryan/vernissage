package com.handicraft.vernissage.domain.product.feature;

import com.handicraft.vernissage.application.IdGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record FeatureText(
        @NotNull String id,
        @NotNull String value
) {
    // не нужен метод of, ибо featureBase его присвоит
}

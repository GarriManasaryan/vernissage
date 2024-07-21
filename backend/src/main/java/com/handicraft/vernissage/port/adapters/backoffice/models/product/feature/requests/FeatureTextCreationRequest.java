package com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.requests;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.handicraft.vernissage.domain.product.feature.FeatureDiscriminator;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.CategoryBackofficeModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public record FeatureTextCreationRequest(
        @NotNull String name,
        @Nullable String description,
        @Nullable String parentId,
        @NotNull List<CategoryFeatureCreationModel> categories,
        @NotNull String value
) implements FeatureCreationRequestInterface {

    @Override
    public @NotNull String discriminator() {
        return FeatureDiscriminator.FEATURE_TEXT.name();
    }

}

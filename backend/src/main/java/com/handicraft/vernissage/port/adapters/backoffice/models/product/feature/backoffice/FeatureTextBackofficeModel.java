package com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.backoffice;

import com.handicraft.vernissage.domain.product.feature.FeatureDiscriminator;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.CategoryBackofficeModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public record FeatureTextBackofficeModel(
        @NotNull String id,
        @NotNull String name,
        @Nullable String description,
        @Nullable String parentId,
        @NotNull List<CategoryBackofficeModel> categories,
        @NotNull String value
)  implements FeatureBaseBackofficeModelInterface{

    @Override
    public @NotNull String discriminator() {
        return FeatureDiscriminator.FEATURE_TEXT.name();
    }

}

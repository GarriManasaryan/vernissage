package com.handicraft.vernissage.port.adapters.backoffice.models.product;

import com.handicraft.vernissage.domain.product.feature.FeatureBase;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.CategoryBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.backoffice.FeatureBaseBackofficeModelInterface;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.price.PriceBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.user.MasterBackofficeModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public record ProductCreationRequest(
        @NotNull String name,
        @Nullable String description,
        @NotNull PriceBackofficeModel price,
        @NotNull MasterBackofficeModel master,
        @NotNull List<CategoryBackofficeModel> categories,
        @NotNull List<? extends FeatureBaseBackofficeModelInterface> features
) {

}

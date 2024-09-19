package com.handicraft.vernissage.port.adapters.backoffice.models.product;

import com.handicraft.vernissage.domain.product.price.Price;
import com.handicraft.vernissage.domain.user.Master;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.CategoryBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.backoffice.FeatureBaseBackofficeModelInterface;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.price.PriceBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.user.MasterBackofficeModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public record ProductBackofficeModel(
        @NotNull String id,
        @NotNull String name,
        @Nullable String description,
        @NotNull PriceBackofficeModel priceBackofficeModel,
        @NotNull MasterBackofficeModel masterBackofficeModel,
        @NotNull List<CategoryBackofficeModel> categories,
        @NotNull List<? extends FeatureBaseBackofficeModelInterface> features

) {

}

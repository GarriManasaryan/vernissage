package com.handicraft.vernissage.port.adapters.backoffice.models.product;

import com.handicraft.vernissage.domain.product.price.Price;
import com.handicraft.vernissage.domain.user.Master;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.CategoryBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.backoffice.FeatureBaseBackofficeModelInterface;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public record ProductBackofficeModel(
        @NotNull String id,
        @NotNull String name,
        @NotNull Optional<String> description,
        @NotNull Price price,
        @NotNull Master master,
        @NotNull List<CategoryBackofficeModel> categories,
        @NotNull List<? extends FeatureBaseBackofficeModelInterface> features

) {

}

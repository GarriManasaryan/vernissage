package com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.backoffice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.CategoryBackofficeModel;

import java.util.List;
import java.util.Optional;

public sealed interface FeatureBaseBackofficeModelInterface permits FeatureNumericBackofficeModel, FeatureTextBackofficeModel {
    String name();
    String description();
    String parentId();
    List<CategoryBackofficeModel> categories();
    String discriminator();
}

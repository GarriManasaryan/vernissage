package com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.backoffice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.CategoryBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.requests.FeatureNumericCreationRequest;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.requests.FeatureTextCreationRequest;

import java.util.List;
import java.util.Optional;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "discriminator")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FeatureTextBackofficeModel.class, name = "FEATURE_TEXT"),
        @JsonSubTypes.Type(value = FeatureNumericBackofficeModel.class, name = "FEATURE_NUMERIC")
})
public sealed interface FeatureBaseBackofficeModelInterface permits FeatureNumericBackofficeModel, FeatureTextBackofficeModel{
    String name();
    String description();
    String parentId();
    List<CategoryBackofficeModel> categories();
    String discriminator();
}

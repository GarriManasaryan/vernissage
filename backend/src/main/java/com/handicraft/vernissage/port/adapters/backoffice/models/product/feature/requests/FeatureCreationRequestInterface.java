package com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.requests;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.handicraft.vernissage.domain.product.feature.FeatureDiscriminator;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.CategoryBackofficeModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY,
//        property = "discriminator"
//)
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = FeatureTextCreationRequest.class),
//        @JsonSubTypes.Type(value = FeatureNumericCreationRequest.class)
//})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "discriminator")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FeatureTextCreationRequest.class, name = "FEATURE_TEXT"),
        @JsonSubTypes.Type(value = FeatureNumericCreationRequest.class, name = "FEATURE_NUMERIC")
})
public sealed interface FeatureCreationRequestInterface permits FeatureTextCreationRequest, FeatureNumericCreationRequest {
    @NotNull String name();
    @Nullable String description();
    @Nullable String parentId();
    @NotNull List<CategoryFeatureCreationModel> categories();
    @NotNull String discriminator();
}

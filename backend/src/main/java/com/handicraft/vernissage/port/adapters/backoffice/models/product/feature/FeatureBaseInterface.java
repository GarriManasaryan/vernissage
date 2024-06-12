package com.handicraft.vernissage.port.adapters.backoffice.models.product.feature;

import com.handicraft.vernissage.domain.product.feature.FeatureDiscriminator;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface FeatureBaseInterface {

    String name();
    Optional<String> description();
    Optional<String> parentId();
    String categoryId();
    String discriminator();

}

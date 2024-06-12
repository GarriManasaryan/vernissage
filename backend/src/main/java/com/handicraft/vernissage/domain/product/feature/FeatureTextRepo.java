package com.handicraft.vernissage.domain.product.feature;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface FeatureTextRepo {

    void save(@NotNull FeatureText featureText);

    Optional<FeatureText> ofId(String id);



    List<FeatureText> all();

}

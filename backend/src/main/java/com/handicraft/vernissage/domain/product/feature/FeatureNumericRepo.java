package com.handicraft.vernissage.domain.product.feature;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface FeatureNumericRepo {

    void save(@NotNull FeatureNumeric featureNumeric);

    List<FeatureNumeric> all();

}

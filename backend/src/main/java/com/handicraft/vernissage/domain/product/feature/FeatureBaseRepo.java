package com.handicraft.vernissage.domain.product.feature;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface FeatureBaseRepo {

    void save(@NotNull FeatureBase featureBase);

    List<FeatureBase> all();

}

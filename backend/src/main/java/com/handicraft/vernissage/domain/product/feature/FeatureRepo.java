package com.handicraft.vernissage.domain.product.feature;

import com.handicraft.vernissage.domain.product.ValueExistsRepo;

import java.util.List;

public interface FeatureRepo extends ValueExistsRepo {

    List<? extends FeatureBase> all();

    void saveFeatureBase(FeatureBase featureBase);

    void saveFeatureNumeric(FeatureNumeric featureNumeric);

    void addCategoriesToFeature(FeatureBase featureBase);

    void saveFeatureText(FeatureText featureText);


}

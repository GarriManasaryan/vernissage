package com.handicraft.vernissage.port.adapters.persistence.models;

import java.util.List;

public class FeatureCategoriesSQLModel {
    public static final String table = "vr_feature_categories";
    public static final String featureIdCol = "feature_id";
    public static final String categoryIdCol = "category_id";

    public static List<String> columns() {
        return List.of(featureIdCol, categoryIdCol);
    }

}



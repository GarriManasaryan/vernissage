package com.handicraft.vernissage.port.adapters.persistence.models;

import java.util.List;

public class ProductFeatureSQLModel {
    public static final String table = "vr_product_features";
    public static final String productIdCol = "product_id";
    public static final String featureIdCol = "feature_id";

    public static List<String> columns() {
        return List.of(
                productIdCol,
                featureIdCol
        );
    }

}

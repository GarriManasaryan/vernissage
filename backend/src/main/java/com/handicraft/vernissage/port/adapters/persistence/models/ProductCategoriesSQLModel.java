package com.handicraft.vernissage.port.adapters.persistence.models;

import java.util.List;

public class ProductCategoriesSQLModel {
    public static final String table = "vr_product_categories";
    public static final String productIdCol = "product_id";
    public static final String categoryIdCol = "category_id";

    public static List<String> columns() {
        return List.of(
                productIdCol,
                categoryIdCol
        );
    }

}

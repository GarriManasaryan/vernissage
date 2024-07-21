package com.handicraft.vernissage.port.adapters.persistence.models;

import java.util.List;

public class FullProductQuerySQLModel {
    public static final String idCol = "id";
    public static final String nameCol = "name";
    public static final String descriptionCol = "description";
    public static final String priceCol = "price";
    public static final String masterCol = "master";
    public static final String categoriesCol = "categories";
    public static final String featuresCol = "features";

    public static List<String> columns() {
        return List.of(idCol, nameCol, descriptionCol, priceCol, masterCol, categoriesCol, featuresCol);
    }

}



package com.handicraft.vernissage.port.adapters.persistence.models;

import java.util.List;

public class ProductSQLModel {
    public static final String table = "vr_products";
    public static final String idCol = "id";
    public static final String nameCol = "name";
    public static final String descriptionCol = "description";
    public static final String masterIdCol = "master_id";
    public static final String priceCol = "price";

    public static List<String> columns() {
        return List.of(idCol, nameCol, descriptionCol, masterIdCol, priceCol);
    }

}



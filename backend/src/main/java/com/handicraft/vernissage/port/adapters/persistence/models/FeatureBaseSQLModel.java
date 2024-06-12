package com.handicraft.vernissage.port.adapters.persistence.models;

import java.util.List;

public class FeatureBaseSQLModel {
    public static final String table = "vr_features";
    public static final String idCol = "id";
    public static final String nameCol = "name";
    public static final String descriptionCol = "description";
    public static final String parentIdCol = "parent_id";
    public static final String categoryIdCol = "category_id";
    public static final String discriminatorCol = "discriminator";

    public static List<String> columns() {
        return List.of(idCol, nameCol, descriptionCol, parentIdCol, categoryIdCol, discriminatorCol);
    }

}



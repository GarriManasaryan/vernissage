package com.handicraft.vernissage.port.adapters.persistence.models;

import java.util.List;

public class FeatureTextSQLModel {
    public static final String table = "vr_features_text";
    public static final String idCol = "id";
    public static final String valueCol = "value";

    public static List<String> columns() {
        return List.of(idCol, valueCol);
    }

}



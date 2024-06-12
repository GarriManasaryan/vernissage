package com.handicraft.vernissage.port.adapters.persistence.models;

import java.util.List;

public class FeatureNumericSQLModel {
    public static final String table = "vr_features_numeric";
    public static final String idCol = "id";
    public static final String fromValueCol = "from_value";
    public static final String toValueCol = "to_value";
    public static final String unitCol = "unit";
    public static final String lessThanTextCol = "less_than_text";
    public static final String moreThanTextCol = "more_than_text";

    public static List<String> columns() {
        return List.of(idCol, fromValueCol, toValueCol, unitCol, lessThanTextCol, moreThanTextCol);
    }

}



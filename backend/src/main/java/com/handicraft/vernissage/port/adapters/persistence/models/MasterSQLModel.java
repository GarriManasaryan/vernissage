package com.handicraft.vernissage.port.adapters.persistence.models;

import java.util.List;

public class MasterSQLModel {
    public static final String table = "vr_masters";
    public static final String idCol = "id";
    public static final String nameCol = "name";
    public static final String descriptionCol = "description";

    public static List<String> columns() {
        return List.of(
                idCol,
                nameCol,
                descriptionCol
        );
    }

}

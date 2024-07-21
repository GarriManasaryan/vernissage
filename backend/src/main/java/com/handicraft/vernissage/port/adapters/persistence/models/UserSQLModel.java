package com.handicraft.vernissage.port.adapters.persistence.models;

import java.util.List;

public class UserSQLModel {
    public static final String table = "vr_users";
    public static final String idCol = "id";
    public static final String nameCol = "name";
    public static final String roleCol = "role";
    public static final String timeZoneCol = "time_zone";

    public static List<String> columns() {
        return List.of(
                idCol,
                nameCol,
                roleCol,
                timeZoneCol
        );
    }

}

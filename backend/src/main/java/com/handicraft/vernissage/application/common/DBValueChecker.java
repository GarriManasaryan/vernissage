package com.handicraft.vernissage.application.common;

import com.handicraft.vernissage.domain.product.ValueExistsRepo;

public class DBValueChecker {

    public DBValueChecker() {
    }

    public static void checkValueInTable(String valueToCheck, ValueExistsRepo repo){
        var nameAlreadyExists = repo.nameAlreadyExists(valueToCheck);
        if (nameAlreadyExists){
            throw new IllegalStateException(STR."Value \'\{valueToCheck}\' already exists");
        }
    }

}

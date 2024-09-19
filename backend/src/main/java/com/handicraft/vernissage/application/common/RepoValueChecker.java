package com.handicraft.vernissage.application.common;

import com.handicraft.vernissage.domain.product.ValueExistsRepo;
import org.springframework.stereotype.Service;

@Service
public class RepoValueChecker {

    public RepoValueChecker() {
    }

    public static void checkValueInTable(String valueToCheck, ValueExistsRepo repo){
        var nameAlreadyExists = repo.nameAlreadyExists(valueToCheck);
        if (nameAlreadyExists){
            throw new IllegalStateException(STR."Value \'\{valueToCheck}\' already exists");
        }
    }

}

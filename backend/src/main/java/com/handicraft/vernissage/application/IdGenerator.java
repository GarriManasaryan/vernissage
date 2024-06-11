package com.handicraft.vernissage.application;

import java.util.UUID;

public class IdGenerator {

    public static String generate(String prefix){
        return STR."\{prefix.substring(0, 3)}-\{UUID.randomUUID()}";
    }

}

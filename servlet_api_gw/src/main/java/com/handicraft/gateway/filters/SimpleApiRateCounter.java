package com.handicraft.gateway.filters;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class SimpleApiRateCounter {

    private static ConcurrentHashMap<String, Integer> rate = new ConcurrentHashMap<>();

    public static void incrementRate(String name){
        var newCount = 1;
        var prevRate = rate.get(name);
        if (prevRate != null){
            newCount += prevRate;
        }
        rate.put(name, newCount);
    }

    public static ConcurrentHashMap<String, Integer> getRate(){
        return rate;
    }

}

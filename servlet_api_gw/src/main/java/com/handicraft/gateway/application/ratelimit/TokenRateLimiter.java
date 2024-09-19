package com.handicraft.gateway.application.ratelimit;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TokenRateLimiter {

    private static final int tokenPerTimeLimit = 3;
    private static Long latestRequestTime = System.currentTimeMillis() / 1000;
    private static final int refillInterval = 10;
    private static final HashMap<String, Long> requesterTokenRate = new HashMap<>();

    private static void refillTokens(String requesterName){
        var currentRequestTime = System.currentTimeMillis() / 1000;
        var spentTime = currentRequestTime - latestRequestTime;

        // если потраченное время меньше refillInterval, то пополнять не нужно. Если больше, то добавить
        if (spentTime >= refillInterval){
            // пох на float, мы же просто вычитаем, ну и пусть будет -0.52, главное будет меньше 0
            var newTokensToAdd = spentTime * tokenPerTimeLimit / refillInterval;
            var previousTokens = putDefaultIfNull(requesterName);

            // если мы наприбавляли больше максимльного кол-ва, то оставить макс токен. Если меньше, то оставить наши расчитанные
            var tokensToAdd = Math.min(previousTokens + newTokensToAdd, tokenPerTimeLimit);

            latestRequestTime = currentRequestTime;

            requesterTokenRate.put(requesterName, tokensToAdd);

        }


    }

    public static boolean isExhausted(String requesterName){
        refillTokens(requesterName);
        requesterTokenRate.put(requesterName, putDefaultIfNull(requesterName) - 1);
        return requesterTokenRate.get(requesterName) <= 0;
    }

    public static HashMap<String, Long> getRequesterTokenRate(){
        return requesterTokenRate;
    }

    private static Long putDefaultIfNull(String requesterName){
        requesterTokenRate.put(requesterName, requesterTokenRate.get(requesterName) != null ? requesterTokenRate.get(requesterName) : tokenPerTimeLimit);
        return requesterTokenRate.get(requesterName);
    }

}

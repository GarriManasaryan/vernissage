//package com.handicraft.gateway.configuration;
//
//import com.github.benmanes.caffeine.cache.Caffeine;
//import io.github.bucket4j.distributed.proxy.AsyncProxyManager;
//import io.github.bucket4j.distributed.remote.RemoteBucketState;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.Duration;
//
//@Configuration
//class RateLimiterConfiguration {
//
//    @Bean
//    public AsyncProxyManager<String> caffeineProxyManager() {
//        Caffeine<String, RemoteBucketState> builder = (Caffeine) Caffeine.newBuilder().maximumSize(100);
//        return new CaffeineProxyManager<>(builder, Duration.ofMinutes(1)).asAsync();
//    }
//}

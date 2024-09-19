package com.handicraft.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.header("X-Request-Id", "\\d+").or().path("/api/**")
                        .filters(f ->
                                f.addResponseHeader("X-TestHeader", "foobar"))
                        // из application yml брать
                        .uri("http://backend1:8080")
                )
                .build();
    }

}

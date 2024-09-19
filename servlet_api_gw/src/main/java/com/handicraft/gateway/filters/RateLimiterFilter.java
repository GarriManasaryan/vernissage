package com.handicraft.gateway.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.handicraft.gateway.application.ratelimit.TokenRateLimiter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@Order(1)
public class RateLimiterFilter implements Filter {

    @Value("${spring.datasource.backend.uri}")
    private String BACKEND_URL;

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String backendUrl = BACKEND_URL + httpRequest.getRequestURI();

        var resourceIsExhausted = TokenRateLimiter.isExhausted(backendUrl);
        System.out.println(TokenRateLimiter.getRequesterTokenRate());
        System.out.println("resourceIsExhausted: " + resourceIsExhausted);

        if (!resourceIsExhausted){
            SimpleApiRateCounter.incrementRate(backendUrl);
            // чтобы пошел на след фильтры
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());

        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write("{\"error\": \"Too many requests\", \"message\": \"You have exceeded the rate limit. Please try again later.\"}");
        writer.flush();

    }


}

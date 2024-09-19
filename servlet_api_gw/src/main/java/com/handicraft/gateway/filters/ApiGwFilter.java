package com.handicraft.gateway.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.stream.Collectors;

@Component
@Order(2)
public class ApiGwFilter implements Filter {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.datasource.backend.uri}")
    private String BACKEND_URL;

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String backendUrl = BACKEND_URL + httpRequest.getRequestURI();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        if (headers.getContentType() == null) {
            headers.setContentType(MediaType.APPLICATION_JSON);
        }

        String requestBody = new BufferedReader(new InputStreamReader(httpRequest.getInputStream()))
                .lines()
                .collect(Collectors.joining("\n"));

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> backendResponse = restTemplate.exchange(
                backendUrl,
                HttpMethod.valueOf(httpRequest.getMethod()),
                entity,
                String.class);

        httpResponse.setStatus(HttpStatus.OK.value());
        if (backendResponse.getBody() != null){
            httpResponse.getWriter().write(backendResponse.getBody());
        }

        filterChain.doFilter(servletRequest,servletResponse);

    }
}

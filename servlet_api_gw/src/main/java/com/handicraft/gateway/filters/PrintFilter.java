package com.handicraft.gateway.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
@Order(3)
public class PrintFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

//        System.out.println("22 This is a Servlet doFilter() Method !");

        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

//        // Get remote data
//        System.out.println("Remote Endpoint : "+ httpRequest.getRequestURI() );
//        System.out.println("Rate" + SimpleApiRateCounter.getRate());

        // Wrap the response to allow for logging
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(httpResponse);

        // Copy the cached content back to the original response
        wrappedResponse.copyBodyToResponse();

    }
}

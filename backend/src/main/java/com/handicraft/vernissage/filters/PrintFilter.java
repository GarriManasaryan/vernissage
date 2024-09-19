package com.handicraft.vernissage.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
//@Order(2)
public class PrintFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        System.out.println("22 This is a Servlet doFilter() Method !");

        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        // Get remote data
        System.out.println("Remote Endpoint : "+ httpRequest.getRequestURI() );
        System.out.println("Remote Host : "+ servletRequest.getRemoteHost());
        System.out.println("Remote Address : "+ servletRequest.getRemoteAddr());

        // Invoke filterChain to execute the next filter inorder.
        filterChain.doFilter(servletRequest,servletResponse);

    }
}

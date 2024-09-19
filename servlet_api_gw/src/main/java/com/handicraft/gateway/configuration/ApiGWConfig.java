//package com.handicraft.gateway.configuration;
//
//import com.handicraft.gateway.filters.ApiGwFilter;
//import com.handicraft.gateway.filters.PrintFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ApiGWConfig {
//
//    @Bean
//    public FilterRegistrationBean<ApiGwFilter> apiGatewayFilter() {
//        FilterRegistrationBean<ApiGwFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new ApiGwFilter());
//        registrationBean.addUrlPatterns("/*");
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean<PrintFilter> printFilterFilter() {
//        FilterRegistrationBean<PrintFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new PrintFilter());
//        registrationBean.addUrlPatterns("/*");
//        return registrationBean;
//    }
//
//}

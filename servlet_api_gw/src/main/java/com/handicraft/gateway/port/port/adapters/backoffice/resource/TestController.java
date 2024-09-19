//package com.handicraft.gateway.port.port.adapters.backoffice.resource;
//
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Collections;
//import java.util.List;
//
//@RestController
//public class TestController {
//
//    public record TestRec(String id){}
//
//    @GetMapping("/api/test")
//    public TestRec get(){
//        return new TestRec("acss");
//    }
//
//    public record Category(
//            @NotNull String id,
//            @NotNull String name,
//            @Nullable String description,
//            @Nullable String parentId
//    ) {}
//
//    @GetMapping("/api/testFromBackend")
//    public TestRec getFromBackend(){
//        RestTemplate restTemplate = new RestTemplate();
//        String uri = "http://backend1:8082/api/product-categories";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        var categories = restTemplate.exchange(uri, HttpMethod.GET, entity, Category[].class).getBody();
//
//        return new TestRec(categories[1].name);
//    }
//
//}

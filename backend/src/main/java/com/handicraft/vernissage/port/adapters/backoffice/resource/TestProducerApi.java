package com.handicraft.vernissage.port.adapters.backoffice.resource;

import com.handicraft.vernissage.port.adapters.backoffice.models.product.ProductCreationRequest;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.CategoryBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.feature.backoffice.FeatureBaseBackofficeModelInterface;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.price.PriceBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.user.MasterBackofficeModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
public class TestProducerApi {

    public record Request(
            @NotNull String name
    ) {

    }

    @PostMapping("/api/producers")
    public void save(@RequestBody Request request) {
        RestTemplate restTemplate = new RestTemplate();
        var uri = "http://backend2:8080/api/consumers";
        // host - название сервиса, а порт - внутренний должен быть, мы же внутри контейнера
        // String uri = "http://backend1:8080/api/clinical-trials";

//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);

        HttpEntity<Request> requests = new HttpEntity<>(request);
        restTemplate.postForObject(uri, request, Request.class);
    }

    @PostMapping("/api/consumers")
    public void save2(@RequestBody Request request) {
        System.out.println(STR."\n\n\{request.name}\n\n");

    }
}

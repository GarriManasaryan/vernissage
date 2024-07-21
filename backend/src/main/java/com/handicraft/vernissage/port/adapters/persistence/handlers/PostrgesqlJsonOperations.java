package com.handicraft.vernissage.port.adapters.persistence.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.handicraft.vernissage.domain.product.price.Price;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class PostrgesqlJsonOperations implements JsonOperations {
    private final ObjectMapper objectMapper;

    public PostrgesqlJsonOperations() {
        this.objectMapper = new ObjectMapper();
    }

    @Nullable
    public List<String> deserializeFromStringToListJson(@Nullable String jsonString){
        try{
            return Arrays.stream(this.objectMapper.readValue(jsonString, String[].class)).toList();
        }
        catch (JsonProcessingException e){
            return null;
        }
    }

    @Override
    public ObjectMapper getObjectMapper(){
        return this.objectMapper;
    }

    @Override
    public <T> T asEntityRowMapper(String jsonString, Class<T> clazz) {
        try {
            return this.objectMapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e){
            throw new IllegalStateException(STR."Object cant be mapped; \{e.getMessage()}");
        }

    }

    @Nullable
    public List<Object> deserializeFromStringToListObjects(@Nullable String jsonString){
        try{
            return Arrays.stream(this.objectMapper.readValue(jsonString, Object[].class)).toList();
        }
        catch (JsonProcessingException e){
            return null;
        }
    }

    @Nullable
    public String serializeFromObjToStringJson(@Nullable Object o){
        Object valueToWrite = o == null ? new ArrayList<>() : o;
        try{
            return this.objectMapper.writeValueAsString(valueToWrite);
        }
        catch (JsonProcessingException e){
            return null;
        }

    }

}

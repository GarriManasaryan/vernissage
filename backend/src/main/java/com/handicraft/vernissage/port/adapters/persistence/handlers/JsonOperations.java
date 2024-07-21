package com.handicraft.vernissage.port.adapters.persistence.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.Nullable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface JsonOperations {

    @Nullable
    List<String> deserializeFromStringToListJson(@Nullable String jsonString);

    ObjectMapper getObjectMapper();

    <T> T asEntityRowMapper(String jsonString, Class<T> clazz);

    @Nullable
    List<Object> deserializeFromStringToListObjects(@Nullable String jsonString);

    @Nullable
    String serializeFromObjToStringJson(@Nullable Object o);

}

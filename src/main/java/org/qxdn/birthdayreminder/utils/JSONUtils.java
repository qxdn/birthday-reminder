package org.qxdn.birthdayreminder.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JSONUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows(JsonProcessingException.class)
    public static String toJSONString(Object object) {
        return objectMapper.writeValueAsString(object);
    }

    @SneakyThrows(JsonProcessingException.class)
    public static <T> T parseObject(String json, Class<T> clazz) {
        return objectMapper.readValue(json, clazz);
    }
}

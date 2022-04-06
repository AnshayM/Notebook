package anshay.notebook.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author machao
 * @date 2021/4/23
 */
@Slf4j
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // 允许出现特殊字符和转义符
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        // 允许出现单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
        //忽略没有的值
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static <T> T toObj(String content, Class<T> clazz) {
        try {
            return objectMapper.readValue(content, clazz);
        } catch (IOException e) {
            log.error("jackson 转对象错误", e);
            return null;
        }
    }

    public static <T> T toObj(Object content, Class<T> clazz) {
        try {
            return objectMapper.readValue(toJsonString(content), clazz);
        } catch (IOException e) {
            log.error("jackson 转对象错误", e);
            return null;
        }
    }

    public static <T> T toObj(String content, TypeReference<T> tr) {
        try {
            return objectMapper.readValue(content, tr);
        } catch (IOException e) {
            log.error("jackson 转对象错误", e);
            return null;
        }
    }

    public static <T> T toObj(Object content, TypeReference<T> tr) {
        try {
            return objectMapper.readValue(toJsonString(content), tr);
        } catch (IOException e) {
            log.error("jackson 转对象错误", e);
            return null;
        }
    }

    public static <T> List<T> toObjList(String content, Class<T> clazz) {
        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        try {
            return objectMapper.readValue(content, javaType);
        } catch (IOException e) {
            log.error("jackson 转对象错误", e);
            return null;
        }
    }

    public static <T> List<T> toObjList(Object content, Class<T> clazz) {
        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        try {
            return objectMapper.readValue(toJsonString(content), javaType);
        } catch (IOException e) {
            log.error("jackson 转对象错误", e);
            return null;
        }
    }

    public static <T> List<T> toObjList(String content, TypeReference<List<T>> tr) {
        try {
            return objectMapper.readValue(content, tr);
        } catch (IOException e) {
            log.error("jackson 转对象错误", e);
            return null;
        }
    }

    public static <T> List<T> toObjList(Object content, TypeReference<List<T>> tr) {
        try {
            return objectMapper.readValue(toJsonString(content), tr);
        } catch (IOException e) {
            log.error("jackson 转对象错误", e);
            return null;
        }
    }

    public static String toJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("jackson 转字符串错误", e);
            return null;
        }
    }

    public static <T> T getVal(JsonNode data, String field, Class<T> clazz) {
        return data.has(field) ? JsonUtil.toObj(data.findValue(field), clazz) : null;
    }

    public static String asText(JsonNode data, String field) {
        return data.has(field) ? data.findValue(field).asText("") : "";
    }

    public static int asInt(JsonNode data, String field) {
        return data.has(field) ? data.findValue(field).asInt(0) : 0;
    }

    public static double asDouble(JsonNode data, String field) {
        return data.has(field) ? data.findValue(field).asDouble(0.0) : 0.0;
    }

    public static double asLong(JsonNode data, String field) {
        return data.has(field) ? data.findValue(field).asLong(0) : 0;
    }

    public static boolean asBoolean(JsonNode data, String field) {
        return data.has(field) && data.findValue(field).asBoolean();
    }
}

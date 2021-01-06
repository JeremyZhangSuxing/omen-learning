package com.omen.learning.common.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weweibuy.framework.common.core.exception.Exceptions;
import com.weweibuy.framework.common.core.model.eum.CommonErrorCodeEum;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author : Knight
 * @date : 2021/1/6 1:33 下午
 */
@Slf4j
public class JackJsonProUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * json 序列化
     *
     * @param jsonString json字符
     * @param clazz      集合对象名称
     * @param <T>        泛型
     * @return 转化的list集合
     */
    public static <T> List<T> convertToList(String jsonString, Class<T> clazz) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
            return objectMapper.readValue(jsonString, javaType);
        } catch (JsonProcessingException e) {
            log.error("json 序列化为 List失败 {}", e.getMessage());
            throw Exceptions.system(CommonErrorCodeEum.JSON_PARSE_EXCEPTION, e);
        }
    }

    /**
     * @param jsonString JSON字符
     * @param clazz      目标对象
     * @param <T>        泛型
     * @return javaObject
     */
    public static <T> T convertToObject(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            log.error("json 序列化为 Object失败 {}", e.getMessage());
            throw Exceptions.system(CommonErrorCodeEum.JSON_PARSE_EXCEPTION, e);
        }
    }

    public static String convertToJson(Object clazz) {
        try {
            objectMapper.writeValueAsString(clazz);
            return objectMapper.writeValueAsString(clazz);
        } catch (JsonProcessingException e) {
            log.error("javaObject 序列化为 jsonString失败 {}", e.getMessage());
            throw Exceptions.system(CommonErrorCodeEum.JSON_WRITE_EXCEPTION, e);
        }
    }
}

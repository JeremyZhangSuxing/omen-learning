package com.omen.learning.common.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.weweibuy.framework.common.core.exception.Exceptions;
import com.weweibuy.framework.common.core.model.eum.CommonErrorCodeEum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author : Knight
 * @date : 2021/1/6 1:33 下午
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JackJsonProUtils {
    private static final ObjectMapper objectMapper = buildDefaultObjectMapper();

    /**
     * 反序列化list集合为string 中含有localDateTime问题
     */
    private static ObjectMapper buildDefaultObjectMapper() {
        return new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

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

    /**
     * 序列化为json 所有对象通用
     */
    public static String convertToJson(Object clazz) {
        try {
            return objectMapper.writeValueAsString(clazz);
        } catch (JsonProcessingException e) {
            log.error("javaObject 序列化为 jsonString失败 {}", e.getMessage());
            throw Exceptions.system(CommonErrorCodeEum.JSON_WRITE_EXCEPTION, e);
        }
    }

    /**
     * 获取通用的类
     */
    public static JavaType getJavaType(Type parameterType, Method method) {
        return objectMapper.constructType(GenericTypeResolver.resolveType(parameterType, method.getReturnType()));
    }
}

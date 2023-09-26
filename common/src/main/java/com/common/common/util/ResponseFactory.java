package com.common.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ResponseFactory {
    private static final Logger log = LoggerFactory.getLogger(ResponseFactory.class);

    public ResponseFactory() {
    }

    public static <T> Response<T> getResponse(int status, String message, T data) {
        Response<T> response = new Response<>();
        response.setStatus(status);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static Response<?> getResponse(int status, String message) {
        return getResponse(status, message, (Object) null);
    }

    public static <T> Response<T> getSuccess(String message, T data) {
        return getResponse(ResponseEnum.success.getCode(), message, data);
    }

    public static <T> Response<T> getSuccessData(T data) {
        return getResponse(ResponseEnum.success.getCode(), ResponseEnum.success.getMessage(), data);
    }

    public static <V> Response<Map<String, V>> getSuccessData(String k1, V v1) {
        Map<String, V> data = new HashMap<>();
        data.put(k1, v1);
        return getResponse(ResponseEnum.success.getCode(), ResponseEnum.success.getMessage(), data);
    }

    public static <V> Response<Map<String, V>> getSuccessData(String k1, V v1, String k2, V v2) {
        Map<String, V> data = new HashMap();
        data.put(k1, v1);
        data.put(k2, v2);
        return getResponse(ResponseEnum.success.getCode(), ResponseEnum.success.getMessage(), data);
    }

    public static <V> Response<Map<String, V>> getSuccessData(String k1, V v1, String k2, V v2, String k3, V v3) {
        Map<String, V> data = new HashMap<>();
        data.put(k1, v1);
        data.put(k2, v2);
        data.put(k3, v3);
        return getResponse(ResponseEnum.success.getCode(), ResponseEnum.success.getMessage(), data);
    }

    public static Response getSuccessMessage(String message) {
        return getResponse(ResponseEnum.success.getCode(), message, (Object) null);
    }

    public static Response getSuccess() {
        return getResponse(ResponseEnum.success.getCode(), ResponseEnum.success.getMessage(), (Object) null);
    }

    public static Response getError(String message) {
        return getResponse(ResponseEnum.error.getCode(), message, (Object) null);
    }

    public static Response getError() {
        return getResponse(ResponseEnum.error.getCode(), ResponseEnum.error.getMessage(), (Object) null);
    }

    public static Response getError(ResponseEnum responseEnum) {
        return getResponse(responseEnum.getCode(), responseEnum.getMessage(), (Object) null);
    }

    public static Response<Map<String, String>> getSmokeEnv() {
        Map<String, String> env = System.getenv();
        Map<String, String> data = new HashMap();
        data.put("BUILD_NUMBER", (String) env.get("BUILD_NUMBER"));
        data.put("BRANCH_NAME", (String) env.get("BRANCH_NAME"));
        data.put("PROJECT_ID", (String) env.get("PROJECT_ID"));
        data.put("SERVICE_NAME", (String) env.get("SERVICE_NAME"));
        return getResponse(ResponseEnum.success.getCode(), ResponseEnum.success.getMessage(), data);
    }
}

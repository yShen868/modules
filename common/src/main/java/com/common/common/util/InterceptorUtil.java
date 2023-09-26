package com.common.common.util;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterceptorUtil {
    private static final Logger log = LoggerFactory.getLogger(InterceptorUtil.class);

    public InterceptorUtil() {
    }

    public static String getFullUrl(HttpServletRequest request) {
        StringBuilder path = new StringBuilder();
        if (request.getAttribute("javax.servlet.forward.servlet_path") != null) {
            path.append(request.getAttribute("javax.servlet.forward.servlet_path").toString());
        } else {
            path.append(request.getRequestURI());
        }

        if (null != request.getQueryString()) {
            path.append("?").append(request.getQueryString());
        }

        return path.toString();
    }

    public static String getHeaderString(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x-real-ip", request.getHeader("x-real-ip"));
        jsonObject.put("apptype", request.getHeader("APPTYPE"));
        jsonObject.put("user-agent", request.getHeader("user-agent"));
        return jsonObject.toJSONString();
    }

}
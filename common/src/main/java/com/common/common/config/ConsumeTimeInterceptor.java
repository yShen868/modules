package com.common.common.config;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.common.common.util.InterceptorUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.util.Locale;

public class ConsumeTimeInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(ConsumeTimeInterceptor.class);
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal("StopWatch-StartTime");
    private long threshold = 500L;

    private final static String APPLICATION_OR_JSON = "application/json";

    public ConsumeTimeInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        long beginTime = System.currentTimeMillis();
        this.startTimeThreadLocal.set(beginTime);
        String fullUrl = InterceptorUtil.getFullUrl(request);


        // 获取body
//        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
//        String body = null;
//        if (StrUtil.isNotBlank(request.getContentType()) && StrUtil.contains(request.getContentType().toLowerCase(Locale.ROOT), APPLICATION_OR_JSON)) {
//            String temp = new String(StreamUtils.copyToByteArray(requestWrapper.getInputStream()));
//            body = StrUtil.isNotBlank(temp) ? temp : null;
//        }
//
//        try {
//            body = JSON.toJSONString(JSON.parseObject(body));
//        } catch (Exception e) {
//
//        }
        log.info("请求URL===={}", fullUrl);
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long endTime = System.currentTimeMillis();
        long beginTime = (Long) this.startTimeThreadLocal.get();
        long consumeTime = endTime - beginTime;
        String fullUrl = InterceptorUtil.getFullUrl(request);
        log.info("请求URL===={},用时={}", fullUrl, consumeTime);
        if (consumeTime > this.threshold) {
            log.warn("time montor:{}", String.format("%s consume %d millis", fullUrl, consumeTime));
        }

    }

    public void setThreshold(long threshold) {
        this.threshold = threshold;
    }
}
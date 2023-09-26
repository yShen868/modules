package com.common.common.config;

import cn.hutool.core.util.StrUtil;
import com.common.common.util.LogUtils;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class MdcInterceptor implements HandlerInterceptor {
    private String requestId = "request-id";

    public MdcInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String preLog = request.getHeader(this.requestId);
        if (StringUtils.isNotEmpty(preLog)) {
            LogUtils.addPreLog(preLog);
        } else {
            LogUtils.addPreLog();
        }

        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LogUtils.rmPreLog();
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}

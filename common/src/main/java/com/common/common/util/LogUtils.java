package com.common.common.util;

import org.slf4j.MDC;

import java.util.UUID;

/**
 * @Author: zhengyuekai
 * @CreateTime: 2023/9/25 15:35
 * @Description:
 */
public class LogUtils {

    public static final String MDC_LOG_PREFIX = "preLog";

    public LogUtils() {
    }

    public static void addPreLog(String preLog) {
        if (null != preLog && preLog.length() != 0) {
            MDC.put("preLog", preLog);
        } else {
            MDC.put("preLog", UUID.randomUUID().toString());
        }

    }

    public static void addPreLog() {
        MDC.put("preLog", UUID.randomUUID().toString());
    }

    public static void rmPreLog() {
        MDC.remove("preLog");
    }

    public static String getPreLog() {
        return MDC.get("preLog");
    }
}

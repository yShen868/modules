package com.common.common.util;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

public class EnvironmentUtil {
    public EnvironmentUtil() {
    }

    public static boolean isDev(Environment env) {
        return env.acceptsProfiles(new String[]{"dev"});
    }

    public static boolean isTest(Environment env) {
        return env.acceptsProfiles(new String[]{"testk8s", "cttest", "xhtest", "xhdemo"});
    }

    public static boolean isProd(Environment env) {
        return env.acceptsProfiles(new String[]{"prodk8s", "ycprod"});
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static boolean isDevOrTest(Environment env) {
        return isDev(env) || isTest(env);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static boolean isTestOrProd(Environment env) {
        return isTest(env) || isProd(env);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static boolean isK8s(Environment env) {
        return env.acceptsProfiles(new String[]{"testk8s", "prodk8s", "cttest", "ycprod", "xhtest", "xhdemo"});
    }

    public static boolean isInAliyun(Environment env) {
        return env.acceptsProfiles(new String[]{"testk8s", "prodk8s", "xhtest", "xhdemo"});
    }

    public static boolean isInCtyun(Environment env) {
        return env.acceptsProfiles(new String[]{"ycprod"});
    }


    /**
     * 当前环境是否生产环境
     */
    public static boolean isProdEnv() {
        Environment env = SpringContextUtils.getBean(Environment.class);
        return EnvironmentUtil.isProd(env);
    }

    /**
     * 当前环境是否生产环境
     */
    public static String getEnv() {

        Environment env = SpringContextUtils.getBean(Environment.class);
        return  env.getProperty("spring.profiles.active");

    }

    /**
     * 当前环境是否生产环境
     */
    public static String getName() {

        Environment env = SpringContextUtils.getBean(Environment.class);
        return  env.getProperty("spring.application.name");

    }
}

package com.common.common.config;

import com.common.common.config.ConsumeTimeInterceptor;
import com.common.common.config.MdcInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * addInterceptor：注册自定义的拦截器；
         * addPathPatterns：需要拦截的路径；
         * excludePathPatterns：排除拦截的路径
         */
        registry.addInterceptor(new MdcInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new ConsumeTimeInterceptor()).addPathPatterns("/**");
    }
}
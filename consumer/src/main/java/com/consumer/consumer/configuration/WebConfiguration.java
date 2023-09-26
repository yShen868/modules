package com.consumer.consumer.configuration;

import com.common.common.config.MdcInterceptor;
import com.provider.provider.entity.Test;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @Author: zhengyuekai
 * @CreateTime: 2023/9/25 15:38
 * @Description:
 */
@Configuration
@ConditionalOnWebApplication(
        type = ConditionalOnWebApplication.Type.SERVLET
)
public class WebConfiguration {


    @Bean
    @ConditionalOnMissingBean
    @Order(Integer.MIN_VALUE)
    public Test testBean() {
        Test test = new Test();
        test.setName("hello");
        test.setId(100);
        return test;
    }
}

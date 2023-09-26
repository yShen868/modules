package com.common.common.config;

import com.common.common.exception.CommonExceptionHandler;
import com.common.common.util.Response;
import com.common.common.util.ResponseFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

/**
 * @Author: zhengyuekai
 * @CreateTime: 2023/9/25 17:30
 * @Description:
 */


@Configuration
@ConditionalOnWebApplication(
        type = ConditionalOnWebApplication.Type.SERVLET
)
public class WebAutoConfiguration {


    @Bean
    public Response responseConf(){
        return ResponseFactory.getSuccessData("成功");

    }


    @Bean
    @ConditionalOnMissingBean(
            name = {"globalDefaultExceptionHandler"}
    )
    public CommonExceptionHandler globalDefaultExceptionHandler(Environment env) {
        return new CommonExceptionHandler(env);
    }

    private String requestId = "request-id";

    @Bean
    @ConditionalOnMissingBean
    @Order(Integer.MIN_VALUE)
    public MdcInterceptor mdcInterceptor() {
        MdcInterceptor interceptor = new MdcInterceptor();
        interceptor.setRequestId(requestId);
        return interceptor;
    }
}

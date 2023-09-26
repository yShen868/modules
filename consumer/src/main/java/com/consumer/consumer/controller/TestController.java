package com.consumer.consumer.controller;

import com.common.common.exception.ServiceException;
import com.common.common.util.EnvironmentUtil;
import com.common.common.util.Response;
import com.common.common.util.ResponseFactory;
import com.consumer.consumer.configuration.ConsumerProperties;
import com.provider.provider.entity.Test;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zhengyuekai
 * @CreateTime: 2023/9/25 15:09
 * @Description:
 */

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {


    @Resource(name = "testBean")
    private Test testBean;


    @Resource(name = "responseConf")
    private Response responseConf;


    @Resource
    private ConsumerProperties consumerProperties;

    @GetMapping("/get")
    public String get() {
        return "test";
    }

    @GetMapping("/new")
    public Test newTest(@RequestParam("name") String name) {
        Test test = new Test();
        test.setId(1);
        test.setName(name);
        return test;
    }

    @GetMapping("/bean")
    public String newTest() {
        log.info("hello:{}", testBean.getName());
        return testBean.getName();
    }


    @PostMapping("/body")
    public Test body(@RequestBody Test test) {
        log.info("name:{}", testBean.getName());
        return test;
    }

    @GetMapping("/error")
    public Response<String> error() {
        log.info("name:{}", testBean.getName());
        throw new ServiceException("手动异常");
//        return ResponseFactory.getSuccessData("hello");
    }


    @GetMapping("/suc")
    public Response<String> suc() {
        return responseConf;
    }


    @GetMapping("/env")
    public Response<Boolean> env() {
        return ResponseFactory.getSuccessData(EnvironmentUtil.isProdEnv());
    }

    @GetMapping("/getEnv")
    public Response<String> getEnv() {
        return ResponseFactory.getSuccessData(EnvironmentUtil.getEnv());
    }

    @GetMapping("/getName")
    public Response<String> getName() {
        return ResponseFactory.getSuccessData(consumerProperties.getName());
    }


}

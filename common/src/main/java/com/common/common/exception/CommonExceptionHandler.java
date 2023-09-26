package com.common.common.exception;

import com.alibaba.fastjson.JSON;
import com.common.common.util.Response;
import com.common.common.util.ResponseFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    private String appName;

    public CommonExceptionHandler(Environment env) {
        this.appName = env.getProperty("spring.application.name");
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response handlerNoHandlerFoundException(NoHandlerFoundException exception) {
        log.error("404,无法找到这个资源", exception);
        return ResponseFactory.getError("无法找到这个资源!");
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Response handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        log.error("405,请求方法不正确", exception);
        return ResponseFactory.getError("请求方法不正确!");
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public Response handlerHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception) {
        log.error("415,不支持该媒体类型", exception);
        return ResponseFactory.getError("不支持该媒体类型!");
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Response handleMissingHttpMessageNotReadableException(Exception ex) {
        log.error("100,请求数据格式不对", ex);
        return ResponseFactory.getError("请求数据格式不对!");
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public Response handleMissingServletRequestParameterException(Exception ex) {
        log.error("必填参数不能为空", ex);
        return ResponseFactory.getError("必填参数不能为空!");
    }


//    @ConditionalOnClass({ValidationException.class})
//    @ExceptionHandler({ValidationException.class})
//    public Response handleValidationException(ValidationException ex) {
//        String result = ex.getMessage();
//        if (ex instanceof ConstraintViolationException) {
//            ConstraintViolationException cex = (ConstraintViolationException)ex;
//            result = (String)cex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
//        }
//
//        logger.error("验证异常:" + result, ex);
//        return ResponseFactory.getError(result);
//    }


    @ExceptionHandler({ServiceException.class})
    public <E extends RuntimeException> Response<?> handleServiceException(ServiceException e) {
        log.error("ex:", e);
        // 在此处处理异常，返回统一的错误响应
        Response response = ResponseFactory.getError(e.getMessage());
        log.info("response:{}", JSON.toJSONString(response));
        return response;
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response<String> handleException(Exception ex) {
        log.error("ex:", ex);
        // 在此处处理异常，返回统一的错误响应
        Response response = ResponseFactory.getError(ex.getMessage());
        log.info("response:{}", JSON.toJSONString(response));
        return response;
    }


}

package com.cbg.scrapy.web.exception;

import com.alibaba.fastjson.JSONObject;
import com.cbg.scrapy.service.exception.CbgBizException;
import com.cbg.scrapy.web.enu.WebResponseStatus;
import com.cbg.scrapy.web.vo.common.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全部异常处理器
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.OK)
    protected WebResponse handleException(RuntimeException e, WebRequest request) {
        log.error("handle exception. ", e);
        return WebResponse.buildError(WebResponseStatus.ERROR);
    }


    /**
     * 业务异常处理器
     */
    @ExceptionHandler(CbgBizException.class)
    @ResponseStatus(value = HttpStatus.OK)
    protected WebResponse handleBizException(BindException e, WebRequest request) {
        log.error("handle CbgBizException. ", e);
        return WebResponse.buildError(WebResponseStatus.ERROR, e.getMessage());
    }

    /**
     * 参数异常统一处理器
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    protected WebResponse handleMethodArgumentException(MethodArgumentNotValidException e, WebRequest request) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuilder buffer = new StringBuilder();
        for(ObjectError error : allErrors) {
            buffer.append(error.getDefaultMessage());
            buffer.append(";");
        }
        log.error("handle MethodArgumentNotValidException, error: {}", buffer.toString(), e);
        return WebResponse.buildError(WebResponseStatus.ERROR, buffer.toString());
    }
}

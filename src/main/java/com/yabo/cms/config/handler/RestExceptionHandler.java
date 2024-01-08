package com.yabo.cms.config.handler;

import com.yabo.cms.entity.Response.R;
import com.yabo.cms.entity.Response.ReturnCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
@ResponseBody
public class RestExceptionHandler {

    /**
     * 处理异常
     *
     * @param e otherException
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R<String> exception(Exception e) {
        log.error("异常 exception = {}", e.getMessage(), e);
        return R.error(ReturnCode.RC500.getCode(), e.getMessage());
    }

    /**
     * 处理404异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public R<String> noHandlerFoundException(HttpServletRequest req, Exception e) {
        log.error("404异常 , method = {}, path = {} ", req.getMethod(), req.getServletPath(), e);
        return R.error(ReturnCode.RC404.getCode(), ReturnCode.RC404.getMsg());
    }
}

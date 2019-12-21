package com.hyp.learn.async.commons;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.async.commons
 * hyp create at 19-12-21
 **/
@ControllerAdvice(annotations = RestController.class)
public class PandaExceptionHandler extends ResponseEntityExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(PandaExceptionHandler.class);

    /**
     * 处理业务异常
     */
    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleException(Exception exception, HttpServletRequest request, HttpServletResponse response) {
        this.logError(exception, request.getRequestURI(), request.getParameterMap());
        RestResult errorBody = RestResult.of(exception);
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    private void logError(Exception exception, String uri, Map<String, String[]> parameterMap) {
        logger.error("request error.request url:" + uri + ". params:" + JSONObject.toJSON(parameterMap).toString());
        logger.error(exception.toString(), exception);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        this.logError(exception, ((ServletWebRequest) request).getRequest().getRequestURI(), request.getParameterMap());
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", exception, 0);
        }
        RestResult errorBody = RestResult.of(exception, status.value());
        return new ResponseEntity(errorBody, headers, status);
    }

    /**
     * 处理javax.validation参数校验不通过异常
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            errors.put(((FieldError) error).getField(), error.getDefaultMessage());
        }
        BusinessException businessException = new BusinessException(ErrorCode.METHOD_ARGUMENT_NOT_VALID, errors);
        return this.handleException(businessException, RequestContextHolderUtil.getRequest(), RequestContextHolderUtil.getResponse());
    }
}


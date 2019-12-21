package com.hyp.learn.test.commons;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.async.commons
 * hyp create at 19-12-21
 **/
public class BusinessException extends RuntimeException implements Serializable {
    private final ErrorCode code;
    private final Map<String, ? extends Object> errors;

    public BusinessException(ErrorCode code, Map<String, Object> errors) {
        super(code.getReason());
        this.code = code;
        this.errors = errors;
    }

    public BusinessException(String message, ErrorCode code, Map<String, ? extends Object> errors) {
        super(message);
        this.code = code;
        this.errors = errors;
    }

    public BusinessException(String message, Throwable cause, ErrorCode code, Map<String, ? extends Object> errors) {
        super(message, cause);
        this.code = code;
        this.errors = errors;
    }

    public BusinessException(Throwable cause, ErrorCode code, Map<String, ? extends Object> errors) {
        super(cause.getMessage(), cause);
        this.code = code;
        this.errors = errors;
    }

    public BusinessException(ErrorCode code) {
        super(code.getReason());
        this.code = code;
        this.errors = new HashMap<>();
    }

    public BusinessException(String message, ErrorCode code) {
        super(message);
        this.code = code;
        this.errors = new HashMap<>();
    }

    public BusinessException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
        this.errors = cause instanceof ServiceException ? ((ServiceException) cause).getErrors() : new HashMap<>();
    }

    public BusinessException(Throwable cause, ErrorCode code) {
        super(cause.getMessage(), cause);
        this.code = code;
        this.errors = cause instanceof ServiceException ? ((ServiceException) cause).getErrors() : new HashMap<>();
    }

    public ErrorCode getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        Map<String, Object> map = new HashMap<>();
        map.put("httpStatus", this.getCode().getHttpStatus());
        map.put("code", this.getCode().getCode());
        map.put("reason", this.getCode().getReason());
        map.put("solution", this.getCode().getSolution());
        map.put("message", this.getMessage());
        return JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    public Map<String, ? extends Object> getErrors() {
        return this.errors;
    }

    public Integer getCodeId() {
        return this.code.getCode();
    }
}


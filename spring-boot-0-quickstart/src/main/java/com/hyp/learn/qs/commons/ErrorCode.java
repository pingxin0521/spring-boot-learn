package com.hyp.learn.qs.commons;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.async.commons
 * hyp create at 19-12-21
 **/
@Getter
public enum ErrorCode {

    // 通用
    METHOD_ARGUMENT_NOT_VALID(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.value(),"参数约束错误","调整参数");

    private Integer httpStatus;
    private Integer code;
    private String reason;
    private String solution;

    ErrorCode(Integer httpStatus, Integer code, String reason, String solution) {
        // FIXME: 自定义异常的 Http Status 统一为 200
        this.httpStatus = HttpStatus.OK.value();
        // 错误码的前缀为期望的HttpStatus
        this.code = code;
        this.reason = reason;
        this.solution = solution;
    }
}

package com.hyp.learn.r.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.async.commons
 * hyp create at 19-12-21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResult {

    private Integer code;
    private String message;
    private Object data;

    public static RestResult of(Object data) {
        return new RestResult(0, "success", data);
    }

    public static RestResult of(Exception e) {
        if (e instanceof BusinessException) {
            return new RestResult(((BusinessException) e).getCodeId(), e.getMessage(), ((BusinessException) e).getErrors());
        } else {
            return new RestResult(500, e.getMessage(), Collections.emptyMap());
        }
    }

    public static RestResult of(Exception e, Integer code) {
        if (e instanceof BusinessException) {
            return new RestResult(((BusinessException) e).getCodeId(), e.getMessage(), ((BusinessException) e).getErrors());
        } else {
            return new RestResult(code, e.getMessage(), Collections.emptyMap());
        }
    }
}

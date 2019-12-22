package com.hyp.learn.wf.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebInputException;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.async.commons
 * hyp create at 19-12-21
 **/
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理 ServiceException 异常
     */
    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult serviceExceptionHandler(ServiceException ex) {
        logger.debug("[serviceExceptionHandler]", ex);
        // 包装 CommonResult 结果
        return CommonResult.error(ex.getCodeId(), ex.getMessage());
    }

    /**
     * 处理 ServerWebInputException 异常
     * <p>
     * WebFlux 参数不正确
     */
    @ResponseBody
    @ExceptionHandler(value = ServerWebInputException.class)
    public CommonResult missingServletRequestParameterExceptionHandler(ServerWebInputException ex) {
        logger.debug("[missingServletRequestParameterExceptionHandler]", ex);
        // 包装 CommonResult 结果
        return CommonResult.error(CommonCode.MISSING_REQUEST_PARAM_ERROR.getCode(),
                CommonCode.MISSING_REQUEST_PARAM_ERROR.getMessage());
    }

    /**
     * 处理其它 Exception 异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult exceptionHandler(Exception e) {
        // 记录异常日志
        logger.error("[exceptionHandler]", e);
        // 返回 ERROR CommonResult
        return CommonResult.error(CommonCode.SYS_ERROR.getCode(),
                CommonCode.SYS_ERROR.getMessage());
    }

}


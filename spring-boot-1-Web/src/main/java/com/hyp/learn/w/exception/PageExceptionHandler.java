package com.hyp.learn.w.exception;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.w.exception
 * hyp create at 19-12-21
 **/

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 传统系统中的有错误界面
 *
 */
@ControllerAdvice
public class PageExceptionHandler {


    /**
     * 返回到错误页面，位于resources>templates>error.html文件
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView pageExceptionHandle(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", e.getMessage());
        modelAndView.addObject("url", req.getRequestURL());
        modelAndView.addObject("stackTrace",e.fillInStackTrace());
        // 模板名称
        modelAndView.setViewName("myerror");
        System.out.println(modelAndView);
        return modelAndView;
    }
}

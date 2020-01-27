package com.hyp.learn.mail.controller;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.mail.controller
 * hyp create at 20-1-24
 **/

import com.hyp.learn.mail.service.MailServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "邮件方法类", tags = "邮件方法类")
@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private MailServiceImpl mailService;

    //接收邮箱地址
    private String to = "xx@163.com";

    @GetMapping("/to")
    public void sendSimpleMail() {
        mailService.sendSimpleMail(to, "主题：简单邮件", "测试邮件内容");
    }

    @GetMapping("/to33")
    public void sendHtmlMail() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("time", System.currentTimeMillis());
        model.put("message", "huhy   测试邮件");
        String content = "111111111111111111111111111111111";

        mailService.sendHtmlMail(to, "主题：html邮件", content);
    }

    @GetMapping("/to3344")
    public void sendAttachmentsMail() {
        mailService.sendAttachmentsMail(to, "主题：带附件的邮件", "有附件，请查收！", "/home/hyp/Documents/pictures/1.jpg");
    }

    @GetMapping("/to334455")
    public void sendInlineResourceMail() {
        String rscId = "huhy";
        mailService.sendInlineResourceMail(to,
                "主题：嵌入静态资源的邮件",
                "<html><body>这是有嵌入静态资源：<img src=\'cid:" + rscId + "\' ></body></html>",
                "/home/hyp/Documents/pictures/1.jpg",
                rscId);
    }


}


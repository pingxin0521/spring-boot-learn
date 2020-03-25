package com.hyp.learn.controller;

import com.hyp.learn.utils.QRCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.controller
 * hyp create at 20-3-9
 **/
@RestController
public class QrCodeController {
    /**
     * 根据 url 生成 普通二维码
     */
    @RequestMapping(value = "/createCommonQRCode")
    public void createCommonQRCode(HttpServletResponse response, String url) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            //使用工具类生成二维码
            QRCodeUtil.encode(url, stream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    /**
     * 根据 url 生成 带有logo二维码
     */
    @RequestMapping(value = "/createLogoQRCode")
    public void createLogoQRCode(HttpServletResponse response, String url) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            String logoPath = Thread.currentThread().getContextClassLoader().getResource("").getPath()
                    + "templates" + File.separator + "logo.jpg";
            //使用工具类生成二维码
            QRCodeUtil.encode(url, logoPath, stream, true);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
}

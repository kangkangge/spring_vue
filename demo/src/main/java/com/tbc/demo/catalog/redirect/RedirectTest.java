package com.tbc.demo.catalog.redirect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping(path = "edirectTest", produces = "application/json;charset=UTF-8")
public class RedirectTest {

    /**
     * 重定向方法ResponseEntity:  使用 ResponseEntity 不需要依赖HttpSerletResponse 在使用了注解 @ResponseBody的情况下 依然有效
     * @return
     */
    @RequestMapping("test1")
    @ResponseBody
    public ResponseEntity redirect1(){
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "https://mp.weixin.qq.com").build();
    }

    /**
     * 重定向方法redirect:  使用 redirect字符串 不需要依赖HttpSerletResponse 在使用了    @ResponseBody 注解的情况下无效;
     * @return
     * @throws IOException
     */
    @RequestMapping("test2")
    @ResponseBody
    public String redirect2(){
        return "redirect:https://www.oschina.net/";
    }

    /**
     * sendRedirect:  使用 response的 sendRedirect 需要依赖HttpSerletResponse
     * @param response
     */

    @RequestMapping("test3")
    @ResponseBody
    public void redirect3(HttpServletResponse response) {
        try {
            response.sendRedirect("https://www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("count")
    public void redirect(HttpServletResponse response) throws InterruptedException {
        log.info("##########         开始调用    redirect1     ##########");
        redirect1();
        Thread.sleep(999);
        log.info("##########         开始调用    redirect2     ##########");
        redirect2();
        Thread.sleep(999);
        log.info("##########         开始调用    redirect3     ##########");
        redirect3(response);
        Thread.sleep(999);
        log.info("##########         调用结束         ##########");
    }





}

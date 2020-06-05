package com.tbc.demo.catalog.redirect;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@Slf4j
@Controller
@RequestMapping(path = "RedirectTest2",produces = "application/json;charset=UTF-8")
public class RedirectTest2 {

    @RequestMapping("get")
    @ResponseBody
    public void get(HttpServletResponse response) throws IOException {
        log.info("##########        get         ##########");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/demo/Redirect");
        CloseableHttpResponse execute = httpClient.execute(httpGet);
        System.out.println(execute.toString());
    }

    @RequestMapping("Redirect")
    public void redirect( HttpServletResponse response) throws IOException {
        log.info("##########        Redirect         ##########");
        response.sendRedirect("http://www.baidu.com");
        log.info("##########         Redirect结束         ##########");
    }
}

package com.tbc.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("webDemo")
@Controller
@Slf4j
public class WebDemo {

    @RequestMapping("test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getCookies().toString());
        response.sendRedirect("https://yufa.21tb.com");
    }

    @RequestMapping("test1")
    public void test1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("http://cloud.21tb.com");
    }
}

package com.tbc.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("webDemo")
@Controller
@Slf4j
public class WebDemo {

    @RequestMapping("test")
    @ResponseBody
    public String test(HttpServletRequest request) {
        log.info(request.getQueryString());
        return request.getQueryString();
    }
}

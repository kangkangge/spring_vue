package com.tbc.demo.catalog.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * 注释
 *
 * @author gekangkang
 * @date 2019/8/23 13:46
 */
@Slf4j
@RequestMapping("demo")
@Controller
public class Demo {

    @RequestMapping("demo")
    public void from(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("test", "懒得开");
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap);
        response.setStatus(404);
        return;
    }
}
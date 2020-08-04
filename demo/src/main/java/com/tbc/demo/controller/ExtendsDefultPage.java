package com.tbc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExtendsDefultPage {

    /**
     * @author gkk
     * @date 2019/7/23 21:21
     * @return java.lang.String
     * @describe  设置spring boot 默认页面 默认返回路径为  resoureces 下的 static路径
     * @version 1.0
     */
    @RequestMapping(value = "/")
    public String homePage() {
        return "/index.html";
    }
}


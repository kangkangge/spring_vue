package com.tbc.demo.catalog.error_page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("error")
@Controller
public class ErrorPage {

    @RequestMapping("404")
    public String error_404(){
        return "/templates/404.html";
    }
}

package com.tbc.demo.catalog.freemarker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gkk
 * @date 2019/7/19 22:18
 * @return
 * @describe freemaker测试demoe
 * @version 1.0
 */
@Controller
@RequestMapping("freemarker")
@Slf4j
public class FreemarkerController {

    @RequestMapping("demo")
    public String freemarkerDemo(ModelMap map) {
        //这里的ModelMap就相当于SpringMVC中的ModelAndView
        map.addAttribute("name","张三");
        //返回路径为模板的路径
        return "freemaker";
    }

}

package com.tbc.demo.catalog.retrun_body;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 注释
 *
 * @author gekangkang
 * @date 2019/7/30 14:53
 */
@RestController
@Slf4j
@RequestMapping("returnBody")
public class ReturnBody {

    /**
     * @author gkk
     * @date 2019/7/30 15:00
     * @params []
     * @return java.util.Map
     * @throws //
     * @describe 加上 @RestController这个注解后所有的方法都会自动转为JSON字符串
     * @version 1.0
     */
    @RequestMapping("body")
    public Map returnBody(){
        Map<String, String> ret = new HashMap<>();
        ret.put("test1","test1");
        ret.put("test2","test2");
        ret.put("test3","test3");
        ret.put("test4","test4");
        ret.put("test5","test5");
        return ret;
    }
}

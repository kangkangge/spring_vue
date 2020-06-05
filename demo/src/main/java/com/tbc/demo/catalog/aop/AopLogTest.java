package com.tbc.demo.catalog.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注释
 *
 * @author a.jia
 * @date 2019/6/12 19:17
 */
@Slf4j
@Controller
@RequestMapping(path = "aop", produces = "application/json;charset=UTF-8")
public class AopLogTest {

    /**
     *  Aop 测试日志打印
     * @param user
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("aopLog")
    public String aopLog(String user,String id) {
        String lalala = invokeTest("lalala");
        return  user+id;
    }

    /**
     * 测试调用方法的打印日志,与request会不会出问题
     *      测试结果AOP是从 A 调用 B 的时候或者外部访问 以一个类为出入点的时候才会打印AOP日志,同一个类间调用方法是不打印日志的     *
     *      *      注入到spring中的bean
     * @param tsr
     * @return
     */
    public String invokeTest(String tsr) {
        return tsr;
    }
}

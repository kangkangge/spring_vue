package com.tbc.demo.common;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import org.aspectj.lang.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于记录日志的工具类，它里面提供了公共的代码
 */
@Slf4j
@Component
@Aspect
public class LoggerAop {

    /**
     * @author gkk
     * @date 2019/6/13 0:04
     * @return void
     * @describe 前置日志,用于打印进入方法的信息
     * @version 1.0
     */
    @Before("pt()")
    public void beforePrintLog(JoinPoint p) {
/*        startTime = System.currentTimeMillis();
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        log.info(st + "url:{},ip:{}", request.getRequestURL(),request.getRemoteAddr() );
        log.info(st + "requestMethod:{}", request.getMethod() );
        log.info(st + "class:{},method:{}", p.getSignature().getDeclaringTypeName(),p.getSignature().getName() );*/
    }

    @AfterReturning("pt()")
    public void afterReturningPrintLog() {
        log.info(st + "方法执行总时长:[{}]毫秒",(System.currentTimeMillis()-startTime));
    }

    /**
     * @author gkk
     * @date 2019/6/13 0:06
     * @return void
     * @describe 用于打印返回值
     * @version 1.0
     */
    @AfterReturning(returning = "result", pointcut = "pt()")
    public void exAfterReturning(Object result) {
        log.info(st + "执行返回值:[{}]",JSONObject.toJSONString(result));
    }


    //日志多的情况下找自己的日志方便!
    private String st = "##########   ";
    //开始进入方法的时间
    private Long startTime = 0L;

    /**
     * 配置切入点:匹配 com.tbc.demo 包下的所有包和子包的所有类,方法
     */
    @Pointcut("execution(* com.tbc.demo..*.*(..))")
    private void pt() {
    }


}
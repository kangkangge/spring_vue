package com.tbc.demo.catalog.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Classname AnnotationDmo
 * @Description TODO
 * @Date 2021/6/15 19:02
 * @Created by gekang
 */
@Aspect
@Slf4j
@Component
public class AnnotationDmo {

    @Before("@annotation(com.tbc.demo.catalog.annotation.Demo)")
    public void Before(JoinPoint point) {
        System.out.println("---------------测试开始---------------");
    }

    @After("@annotation(com.tbc.demo.catalog.annotation.Demo)")
    public void After(JoinPoint point) {
        System.out.println("---------------测试结束---------------");
    }
}

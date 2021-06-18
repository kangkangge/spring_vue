package com.tbc.demo.catalog.fanse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname ApiOperation
 * @Date 2021/6/18 13:02
 * @Created by gekang
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiOperation {
    String value() default "";
}

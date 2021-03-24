package com.tbc.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 注释
 *
 * @author gekangkang
 * @date 2019/7/30 11:42
 */
@Slf4j
public class UrlUtils {

    @Test
    public void main() {
        Method[] methods = this.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals("getString")) {
                new ArrayList<>();
            }
        }

    }


    public void getString(String str, List arrayList) {
        System.out.println(str);

    }
}

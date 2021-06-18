package com.tbc.demo.catalog.fanse;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Classname FanSe
 * @Description 测试反射
 * @Date 2021/6/18 13:00
 * @Created by gekang
 */
public class FanSe {

    public static void main(String[] args) throws Exception {
        FanSeUser fanSeUser = new FanSeUser();
        fanSeUser.setPassword("123132");
        fanSeUser.setUserName("张三");
        Map userMap = getUserMap(fanSeUser, ApiOperation.class);
        System.out.println(userMap);
    }

    private static <T> Map getUserMap(Object obj, Class<ApiOperation> apiOperation) throws Exception {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        Map json = new JSONObject();
        List<Field> fields = Arrays.asList(obj.getClass().getDeclaredFields());
        Method[] declaredMethods = obj.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);
            String methodName = declaredMethod.getName().toLowerCase();
            for (Field field : fields) {
                field.setAccessible(true);
                if (methodName.equals("get" + field.getName().toLowerCase())) {
                    ApiOperation annotation = field.getAnnotation(apiOperation);
                    Object invoke = declaredMethod.invoke(obj);
                    json.put(annotation.value(), invoke);
                }
            }
        }
        return json;
    }
}

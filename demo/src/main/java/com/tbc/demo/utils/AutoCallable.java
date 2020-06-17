package com.tbc.demo.utils;

import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 葛康康
 * 2020年4月16日22:30:32
 *
 * @param <T>
 */
@Data
public class AutoCallable<T> implements Callable<T> {
    private Object obj;
    private String methodName;
    private Object[] params;

    /**
     * 通过反射调用传入对象的指定方法,参数传入
     *
     * @param obj
     * @param curMethodName
     * @param params
     * @return
     */
    public static Object execute(Object obj, String curMethodName, Object... params) {
        if (obj == null || StringUtils.isEmpty(curMethodName)) {
            throw new IllegalArgumentException();
        }
        Class[] parameterTypes = null;
        Object[] paramsArr = null;
        Class<?> objCalzz = obj.getClass();
        if (null != params) {
            parameterTypes = new Class[params.length];
            paramsArr = new Object[params.length];
            List<Method> methods = Arrays.stream(objCalzz.getDeclaredMethods())
                    .filter(method -> method.getName().equals(curMethodName))
                    .filter(method -> method.getParameters().length == params.length)
                    .collect(Collectors.toList());
            for (int i = 0; i < methods.size(); i++) {
                parameterTypes = methods.get(i).getParameterTypes();
                for (int i1 = 0; i1 < parameterTypes.length; i1++) {
                    try {
                        paramsArr[i] = parameterTypes[i].cast(params[i]);
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        }
        try {
            Method func = objCalzz.getDeclaredMethod(curMethodName, parameterTypes);
            func.setAccessible(true);
            return func.invoke(obj, paramsArr);
        } catch (NoSuchMethodException | IllegalAccessException |
                InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 线程池,执行的时候会自动调用该方法
     *
     * @return
     */
    @Override
    public T call() {
        Object execute = execute(obj, methodName, params);
        return execute == null ? null : (T) execute;
    }

    /**
     * @param obj        调用方法对象的实例
     * @param methodName 方法的名称
     * @param params     方法的参数,不可为null,长度必须一致
     */
    public AutoCallable(Object obj, String methodName, Object... params) {
        this.obj = obj;
        this.methodName = methodName;
        this.params = params;

    }

    /**
     * 自动构建 Callable对象
     *
     * @param obj
     * @param methodName
     * @param params
     * @return
     */
    public static AutoCallable build(Object obj, String methodName, Object... params) {
        return new AutoCallable(obj, methodName, params);
    }

}







package com.tbc.demo.utils;

import lombok.Data;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
     * @param obj 调用方法的实例对象
     * @param curMethodName 调用的方法类型名称
     * @param params 方法需要传入的参数(注意:不可以传入null,参数必须全部传入否则会提示找不到方法)
     * @return
     */
    public static Object execute(Object obj, String curMethodName, Object... params) throws Exception {
        Assert.notNull(obj, "调用对象不可为空!");
        Assert.hasText(curMethodName, "调用方法名不可为空!");
        //参数类型的数据
        Class[] parameterTypes = null;
        //参数类型的集合
        List paramsArr = new ArrayList();
        Class<?> objCalzz = obj.getClass();
        if (null != params) {
            parameterTypes = new Class[params.length];
            //根据传入的方法名称与参数长度找到参数(因为有方法重载所需需要判断)
            List<Method> methods = Arrays.stream(objCalzz.getDeclaredMethods())
                    .filter(method -> method.getName().equals(curMethodName))
                    .filter(method -> method.getParameters().length == params.length)
                    .collect(Collectors.toList());
            //goto语法退出多层循环使用!
            tag:
            //参数类型转换,解决子类传入提示找不到方法的情况,如果传入的参数类型能够转换为方法的参数类型就直接强转
            for (int i = 0; i < methods.size(); i++) {
                paramsArr.clear();
                parameterTypes = methods.get(i).getParameterTypes();
                for (int i1 = 0; i1 < parameterTypes.length; i1++) {
                    try {
                        //把传入的参数转换为方法所需要的类型,如果转换失败就无法添加成功,跳出当前的循环!
                        paramsArr.add(i, parameterTypes[i].cast(params[i]));
                        //如果参数匹配完毕就退出
                        if (paramsArr.size() == parameterTypes.length) {
                            break tag;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
            if (parameterTypes.length != paramsArr.size()) {
                throw new IllegalArgumentException("方法参数类型传入错误!");
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
    public T call() throws Exception {
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







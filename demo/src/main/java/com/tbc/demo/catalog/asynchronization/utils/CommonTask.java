package com.tbc.demo.catalog.asynchronization.utils;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * 葛康康
 * 2020年4月16日22:30:32
 * 通用callable类
 *
 * @param <T>返回值类型
 */
@Data
@Builder
public class CommonTask<T> implements Callable<T>, Supplier<T> {
    //调用的对象
    private Object obj;
    //调用的方法
    private String methodName;
    //方法需要传入的参数
    private Object[] params;
    //返回的结果
    private T result;
    //返回的结果
    private List<T> results;
    //阻塞等待的时间
    private static long WAIT_TIME = 10L;
    //开启的线程数量
    private int threadNumber;
    //是否需要汇总
    private Boolean isCollect;


    /**
     * 重写call给future使用
     *
     * @return
     */
    @Override
    public T call() {
        Object execute = execute(obj, methodName, params);
        return execute == null ? null : (T) execute;
    }

    public CommonTask(Object obj, String methodName, Object[] params, T result, List<T> results, int threadNumber, Boolean isCollect) {
        this.obj = obj;
        this.methodName = methodName;
        this.params = params;
        this.result = result;
        this.results = results;
        this.threadNumber = threadNumber;
        this.isCollect = isCollect;
    }

    public CommonTask() {
    }

    public static CommonTask build(Object obj, String methodName, Object... params) {
        CommonTask commonTask = new CommonTask();
        commonTask.setObj(obj);
        commonTask.setParams(params);
        commonTask.setMethodName(methodName);
        return commonTask;
    }

    /**
     * 给 completebleFure使用
     *
     * @return
     */
    @Override
    public T get() {
        return call();
    }

    /**
     * 增加future get方法阻塞超时判断
     *
     * @param t
     * @param <V>
     * @return
     */
    public static <V> V getFuture(Future<V> t) {
        V t1 = null;
        try {
            t1 = t.get(WAIT_TIME, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return t1 == null ? null : t1;
    }


    /**
     * 自动执行传入的方法
     *
     * @param obj        调用的对象
     * @param methodName 调用的方法
     * @param params     入参
     * @return 返回执行的结果
     */
    public static Object execute(Object obj, String methodName, Object... params) {
        if (obj == null || StringUtils.isEmpty(methodName)) {
            throw new IllegalArgumentException();
        }
        Class[] calzzs = null;
        if (null != params) {
            calzzs = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                calzzs[i] = params[i].getClass();
            }
        }
        try {
            Class<?> aClass = obj.getClass();
            Method func = aClass.getDeclaredMethod(methodName, calzzs);
            func.setAccessible(true);
            return func.invoke(obj, params);
        } catch (NoSuchMethodException | IllegalAccessException |
                InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}




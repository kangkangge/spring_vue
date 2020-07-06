package com.tbc.demo.catalog.asynchronization.utils;


import org.junit.Test;

import java.util.concurrent.*;

public class ThreadsUtils {

    /**
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     */
    public static final ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(80);
    private static final long WAIT_TIME = 10L;

    /**
     * 自动根据参数调用对应的 callable对象,实现免创建队callble
     *
     * @param obj        调用的对象
     * @param methodName 调用的方法
     * @param params     方法的参数
     * @param <T>        返回的类型
     * @return 自动返回对应类型的结果
     */
    public static <T> T autoExecute(Object obj, String methodName, Object... params) {
        return (T) newFixedThreadPool.submit(CommonTask.build(obj, methodName, params));
    }

    /**
     * 自动执行并自动获取结果参数,注意:获取参数的时候会阻塞
     */
    public static <T> T autoExecuteAndGetResutl(Object obj, String methodName, Object... params) {
        return (T) CommonTask.getFuture(newFixedThreadPool.submit(CommonTask.build(obj, methodName, params)));
    }

    @Test
    public <T> void execute() throws ExecutionException, InterruptedException {

    }

    @Test
    public void test() {
        StackTraceElement[] s = new Exception().getStackTrace();
        System.out.println(s[1].getClassName() + s[1].getMethodName());
        System.out.println("执行完毕");

    }

    public String test1(String msg) {
        return "0000";
    }


}

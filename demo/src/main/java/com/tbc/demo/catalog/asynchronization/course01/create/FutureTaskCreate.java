package com.tbc.demo.catalog.asynchronization.course01.create;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 * 线程创建方式与调用方法
 */
public class FutureTaskCreate {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1. 接口调用
        TreadRunnable treadRunnable = new TreadRunnable("接口");
        new Thread(treadRunnable).start();
        //2. 继承调用
        new ExtendsCreate("继承").start();
        //3. futureTask + Callable
        FutureTask<String> callable = new FutureTask<>(new CallableCreate("Callable"));
        new Thread(callable).start();
        //get()会阻塞并等待线程完成之后响应结果,可以传入参数设定最大等待时间
        System.out.println("futureTask + Callable 方法执行的返回值" + callable.get());
    }

}



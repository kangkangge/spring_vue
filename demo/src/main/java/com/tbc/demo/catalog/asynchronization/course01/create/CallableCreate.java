package com.tbc.demo.catalog.asynchronization.course01.create;

import java.util.concurrent.Callable;

/**
 * 第三种方式: 使用callable实现call方法,可以有返回值
 */
class CallableCreate implements Callable<String> {
    String threadName;

    // 与run()方法不同的是，call()方法具有返回值
    @Override
    public String call() {
        System.out.println(threadName + ":线程运行了");
        return threadName;
    }

    public CallableCreate(String threadName) {
        super();
        this.threadName = threadName;
    }
}

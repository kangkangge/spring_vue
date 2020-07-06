package com.tbc.demo.catalog.asynchronization.course01.create;

/**
 * 第二种方式: 继承Thread重写Run方法
 */
class ExtendsCreate extends Thread {
    String threadName;

    public void run() {
        System.out.println(threadName + ":线程运行了");
    }

    public ExtendsCreate(String threadName) {
        super();
        this.threadName = threadName;
    }
}

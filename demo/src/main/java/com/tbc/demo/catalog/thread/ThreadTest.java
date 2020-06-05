package com.tbc.demo.catalog.thread;

/**
 * 多线程测试
 */
public class ThreadTest {


    public static void main(String[] args) {
        while (true) {
            for (int i = 0; true; i++) {
                create(Integer.toString(i));
            }
        }
    }

    /**
     * 1.创建线程
     */
    public static void create(String msg) {
        //老方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(msg);
            }
        }).start();
        //labdam
        new Thread(() -> {
            System.out.println(msg);
        }).start();
    }
}

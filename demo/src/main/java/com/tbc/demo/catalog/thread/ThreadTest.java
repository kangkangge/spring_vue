package com.tbc.demo.catalog.thread;

import com.tbc.demo.catalog.asynchronization.course01.create.TreadRunnable;
import org.junit.jupiter.api.Test;

/**
 * 多线程测试
 */
public class ThreadTest {
    public String LOCK = "123";
    public static Boolean flag = true;
    public Thread productor = null;

    @Test
    public void main() throws InterruptedException {
        test();
        Thread.sleep(1000);
        flag = false;
        synchronized (LOCK) {
            LOCK.notify();
        }

    }

    public void test() {
        Thread thread = new Thread(new TreadRunnable("测试线程"));
        thread.start();
        thread.run();
        synchronized (LOCK) {
            //flag模拟工作
            while (flag) {
                try {
                    System.out.println("等待中");
                    thread.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("开始执行");
        }
    }
}

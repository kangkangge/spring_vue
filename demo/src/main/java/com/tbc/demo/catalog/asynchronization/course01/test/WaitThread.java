package com.tbc.demo.catalog.asynchronization.course01.test;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 1.线程等待与唤醒实例
 */
@Slf4j
public class WaitThread {
    static boolean flag = true;
    static Object lock = new Object();
    /**
     * Wait() 导致当前线程等待，直到另一个线程调用该对象的notify()方法或notifyAll()方法
     */
    public static void main(String[] args) throws Exception {
        Thread waitThread = new Thread(new Wait(), "消费者");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "生产者");
        notifyThread.start();

    }


    /**
     * 等待方通知遵循原则
     * 1 获取对象的锁。
     * 2 如果条件不满足，那么调用对象的wait()方法，被通知后仍要检查条件。
     * 3 条件满足则执行对应的逻辑。
     */
    static class Wait implements Runnable {
        public void run() {
            // 加锁，拥有lock的Monitor
            synchronized (lock) {
                // 当条件不满足时，继续wait，同时释放了lock的锁
                while (flag) {
                    try {
                        log.info(" 执行条件为:{} , 当前时间为:{}", flag, new SimpleDateFormat("HH: mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                    }
                }
                // 条件满足时，完成工作
                log.info("任务来了开始至执行任务,当前时间为:{}", new SimpleDateFormat(" HH:mm:ss").format(new Date()));
            }
        }
    }

    /**
     * 通知方遵循如下原则。
     * 1 获得对象的锁。
     * 2 改变条件。
     * 3 通知所有等待在对象上的线程。
     */
    static class Notify implements Runnable {
        public void run() {
            // 加锁，拥有lock的Monitor
            synchronized (lock) {
                // 获取lock的锁，然后进行通知，通知时不会释放lock的锁，
                // 直到当前线程释放了lock后，WaitThread才能从wait方法中返回
                log.info(" 保持锁定,休眠1秒,当前时间为 :{} ", new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notify();
                flag = false;
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 再次加锁
            synchronized (lock) {
                log.info(" 保持锁定,休眠1秒 " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

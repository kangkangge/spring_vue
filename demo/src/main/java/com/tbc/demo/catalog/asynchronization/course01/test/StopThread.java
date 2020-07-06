package com.tbc.demo.catalog.asynchronization.course01.test;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static com.tbc.demo.catalog.asynchronization.course01.test.ThreadLife.getTestThread;


/**
 * 测试终止线程
 */
@Data
@Slf4j
public class StopThread {

    /**
     * 测试安全的停止线程
     *
     * @throws InterruptedException
     */

    @Test
    public void testStop() throws InterruptedException {
        //第一种方式,使用中断标识
        Runner one = new Runner();
        Thread countThread = new Thread(one, "CountThread");
        countThread.interrupt();
        countThread.start();
        // 睡眠1秒，main线程对CountThread进行中断，使CountThread能够感知中断而结束
        TimeUnit.SECONDS.sleep(1);
        log.error("第一种方式中断");
        countThread.interrupt();
        //第二章方式,自定义循环方式
        Runner two = new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();
        // 睡眠1秒，main线程对Runner two进行取消，使CountThread能够感知on为false而结束
        TimeUnit.SECONDS.sleep(1);
        log.error("第二种方式中断");
        two.cancel();
    }



    /**
     * 测试中断API
     *
     * 中断线程:中断可以理解为线程的一个标识位属性，它表示一个运行中的线程是否被其他线程进行了中断操作。
     * 每个线程都有一个与之相关联的 Boolean 属性，用于表示线程的中断状态（interrupted status）。中断状态初始时为 false；
     * 当另一个线程通过调用 Thread.interrupt() 中断一个线程时，会出现以下两种情况之一。如果那个线程在执行一个低级可中断阻塞方法，
     * 例如 Thread.sleep()、 Thread.join() 或 Object.wait()，那么它将取消阻塞并抛出 InterruptedException。否则， interrupt()
     * 只是设置线程的中断状态。 在被中断线程中运行的代码以后可以轮询中断状态，看看它是否被请求停止正在做的事情。中断状态可以通过 Thread.isInterrupted()
     * 来读取，并且可以通过一个名为 Thread.interrupted() 的操作读取和清除。
     */
    @Test
    public void stopThread() {
        //interrupted:  测试当前线程是否已经中断。线程的中断状态由该方法清除。
        //isInterrupted:测试线程是否已经中断。
        //interrupt:    中断线程，但是没有返回结果。是唯一能将中断状态设置为true的方法。
        Thread b = getTestThread("b");
        b.start();
        log.info("初始状态:" + b.interrupted());
        b.interrupt();
        log.info("中断后的状态:" + b.interrupted());
        log.info("重置中断状态:" + b.isInterrupted());
        log.info("重置后状态:" + b.interrupted());
        log.info(":" + b.isInterrupted());

    }

}


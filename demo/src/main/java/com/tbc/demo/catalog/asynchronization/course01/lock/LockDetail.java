package com.tbc.demo.catalog.asynchronization.course01.lock;


import com.tbc.demo.catalog.asynchronization.model.Ticket;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁测试
 */
public class LockDetail {


    public static void main(String[] args) {
        test(true);
    }

    /**
     * 测试公平锁与非公平锁
     *  测试结果 : new ReentrantLock(true)当传true的时候为公平锁,两个线程轮流执行
     */
    @Test
    public static void test(Boolean istrue){
        Ticket r1 = Ticket.builder().reentrantLock(new ReentrantLock(istrue)).build();
        Thread thread = new Thread(r1);
        Thread thread1 = new Thread(r1);
        thread.start();
        thread1.start();
    }
}


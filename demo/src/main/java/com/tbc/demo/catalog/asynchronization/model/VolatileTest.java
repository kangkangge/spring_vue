package com.tbc.demo.catalog.asynchronization.model;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Builder
@Slf4j
class VolatileTest implements Runnable {
    private static volatile double ticket = 999L;
    private String name;
    //测试锁
    private static ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        reentrantLock.lock();
        for (int i = 0; i < ticket; i++) {
            if (i == 999) {
                log.info("票以售完");
            }
            log.info(name + "线程售出:[ " + i + " ]号票");
        }
        reentrantLock.unlock();
    }

    //外部
    synchronized void setA() throws Exception {
        Thread.sleep(1000);
        setB();
    }
    //内部
    synchronized void setB() throws Exception {
        Thread.sleep(1000);
    }
}

package com.tbc.demo.catalog.asynchronization.course01.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. 线程锁问题模拟
 */
@Slf4j
public class SellTickets extends Thread {
    //static 修饰只全局共享一份;
    static int tickets = 1;
    List array = new ArrayList();

    public SellTickets(String threadName) {
        super(threadName);
    }

    public void run() {
        synchronized ("suo") {
            while (true) {
                if (tickets >= 30) {
                    log.info("票已经卖完啦-_-...");
                    break;
                }
                log.info(Thread.currentThread().getName() + "卖了第" + tickets + "号票");

                tickets++;
            }
        }
    }

    //同步方法
    public synchronized void demo() {
        //同步代码块
        synchronized ("suo") {

        }

    }
}

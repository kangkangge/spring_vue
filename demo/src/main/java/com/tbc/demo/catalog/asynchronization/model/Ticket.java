package com.tbc.demo.catalog.asynchronization.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 售票测试
 */
@Slf4j
@Data
@Builder
public class Ticket implements Runnable {
    private static int count = 999;
    private String name;
    //通过fair传true或者false设定是否是公平锁,如果不传默认是非公平锁.非公平锁效率更高
    private ReentrantLock reentrantLock;
    private String switch1;

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            switch (switch1) {
                case "test":
                    log.info("测试公平锁");
                    test1(i);
                    break;

                default:
                    log.info("测试无锁");
                    test(i);
                    break;
            }
        }
    }

    public void test(int i) {
        if (i == count) {
            log.info("票已售完");
        } else {
            log.info("正在售票 : [" + i + " ]号票");
            if (reentrantLock != null) {
                reentrantLock.lock();
            }
        }
    }

    //测试公平锁
    public void test1(int i) {
        if (this.reentrantLock != null) {
            this.reentrantLock.lock();
        }
        test(i);
        if (this.reentrantLock != null) {
            this.reentrantLock.unlock();
        }
    }
}

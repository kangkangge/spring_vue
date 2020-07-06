package com.tbc.demo.catalog.asynchronization.course01.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Runner implements Runnable {
    private static volatile long i;
    private volatile boolean on = true;

    @Override
    public void run() {
        while (on && !Thread.currentThread().isInterrupted()) {
            i++;
        }
        for (i = 0; i < 100000; i++) {
            log.info("第" + i);
            if (i == 100000) {
                log.error("执行完毕");
            }
        }
        System.out.println("Count i = " + i);
    }

    public void cancel() {
        on = false;
    }
}

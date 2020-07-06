package com.tbc.demo.catalog.asynchronization.course02.pool.asyn;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Job implements Runnable {
    @Override
    public void run() {
        log.info("hello world");
    }
}

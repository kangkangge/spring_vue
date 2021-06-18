package com.tbc.demo.catalog.jvm;

/**
 * 测试日志
 */
public class GCPrint {
    public static void main(String[] args) {
        for (int i = 0; i < 1000000000; i++) {
            Users users = new Users();
        }
    }
}

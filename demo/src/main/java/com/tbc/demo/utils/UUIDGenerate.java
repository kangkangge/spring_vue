package com.tbc.demo.utils;

import java.util.UUID;

public class UUIDGenerate {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

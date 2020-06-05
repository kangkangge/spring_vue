package com.tbc.demo.catalog.jedis;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 注释
 *
 * @author gekangkang
 * @date 2019/10/31 14:04
 */
@Slf4j
public class jedis {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.0.18", 6388);
        jedis.rpush("testkey", "12312321");
        String testkey = jedis.get("testkey");
        System.out.println(testkey);
    }


    private static byte[] serialize(Object object) {
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            byte[] getByte = byteArrayOutputStream.toByteArray();
            return getByte;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object unserizlize(byte[] binaryByte) {
        ObjectInputStream objectInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        byteArrayInputStream = new ByteArrayInputStream(binaryByte);
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object obj = objectInputStream.readObject();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

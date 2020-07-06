package com.tbc.demo.catalog.jedis;


import com.tbc.demo.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.io.*;

/**
 * 注释
 *
 * @author gekangkang
 * @date 2019/10/31 14:04
 */
@Slf4j
public class jedis {

    public static void main(String[] args) {
        Jedis jedis = RedisUtils.getJedis();
        jedis.sadd("test", "test1");
        jedis.sadd("test", "test2");
        jedis.sadd("test", "test3");
        jedis.sadd("test", "test4");
        jedis.sadd("test", "test5");
        Boolean sismember = jedis.sismember("test", "test6");
        System.out.println(sismember);
    }


    //序列化
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
    //反序列化
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

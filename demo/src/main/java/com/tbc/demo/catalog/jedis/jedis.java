package com.tbc.demo.catalog.jedis;


import com.tbc.demo.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

import java.io.*;
import java.util.List;
import java.util.Set;

/**
 * 注释
 *
 * @author gekangkang
 * @date 2019/10/31 14:04
 */
@Slf4j
public class jedis {
    Jedis jedis = RedisUtils.getJedis();

    public static void main(String[] args) {
        Jedis jedis = RedisUtils.getJedis();

    }

    /**
     * 测试Hash (map)类型
     */
    @Test
    public void hashTest() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                jedis.hset("test1", "test" + i, "1" + j);
            }
        }
        Set<String> test1 = jedis.hkeys("test1");
        for (String s : test1) {
            String test11 = jedis.hget("test1", s);
            System.out.println(test11);

        }
        System.out.println(test1);
    }

    /**
     * 测试rpush插入顺序与获取顺序
     */
    @Test
    public void listTest() {
        for (int i = 0; i < 10; i++) {
            jedis.rpush("test3", "" + i);
        }
        System.out.println(jedis.llen("test3"));
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

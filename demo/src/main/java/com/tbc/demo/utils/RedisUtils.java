package com.tbc.demo.utils;

import redis.clients.jedis.Jedis;

public class RedisUtils {
    public static Jedis getJedis() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        return jedis;
    }
}

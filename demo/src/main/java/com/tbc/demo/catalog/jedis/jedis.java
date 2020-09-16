package com.tbc.demo.catalog.jedis;


import com.tbc.demo.utils.RandomUtil;
import com.tbc.demo.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Test
    public void opsTest() throws InterruptedException {
        String str = "尊敬的@TIP_TARGET@：你的密码重置验证码为 @VERIFICATIONCODE@,请在30分钟内使用（请确认是本人操作且为本人手机，否则请忽略此短信）。[学习平台]。";
        List<String> smsTemplateFormalParameter = getSmsTemplateFormalParameter(str);
        System.out.println(smsTemplateFormalParameter);
    }
    private List<String> getSmsTemplateFormalParameter(String smsTemplateContent) {// TODO: 2018/11/5
        if (StringUtils.isEmpty(smsTemplateContent)) {
            return null;
        }
        List<String> parameterList = new ArrayList<>();
        //匹配 @xxx@ 格式
        String regex = "@[a-zA-Z]+([_\\.][a-zA-Z]+){0,3}@";
        Pattern p = Pattern.compile(regex);
        // 用Pattern类的matcher()方法生成一个Matcher对象
        Matcher m = p.matcher(smsTemplateContent);
        boolean result = m.find();
        while (result) {
            String parameter = m.group().trim();
            //去除 首尾的 @符号
            parameter = parameter.substring(1, parameter.length() - 1).trim();
            parameterList.add(parameter);
            result = m.find();
        }
        return parameterList;
    }
}

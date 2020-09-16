package com.tbc.demo.catalog.yinlian;

import com.alibaba.fastjson.JSONObject;
import com.tbc.demo.catalog.jedis.jedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("test")
@Slf4j
public class Test {

    public JedisCluster getJedis() {
        return new JedisCluster(new HostAndPort("172.21.0.19", 16382));
    }

    @org.junit.Test
    public void test() {
        JedisCluster jedis = getJedis();
        ImSms imSms = new ImSms();
        imSms.setSmsTempId("JSM40453-0105");
        imSms.setPhoneNumber("17317105627");
        imSms.setSmsContent("@SECURITY_CODE@=654325");
//        imSms.setSmsContent("你好,您的验证码是 @SECURITY_CODE@=123456，10分钟内有效，请勿泄露。");
        String string = JSONObject.toJSONString(imSms);
        System.out.println(string);
        jedis.rpush("redis_queue_sms_quick_new", string);
    }
}

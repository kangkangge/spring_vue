package com.tbc.demo.catalog.ucloud;

import cn.ucloud.common.pojo.Account;
import cn.ucloud.usms.client.DefaultUSMSClient;
import cn.ucloud.usms.model.*;
import cn.ucloud.usms.pojo.USMSConfig;
import com.alibaba.fastjson.JSON;
import com.tbc.demo.catalog.unionpayLogin.WxCommonTest;
import com.tbc.demo.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 优刻得对接
 */
@Slf4j
public class UCloudTest {

    private static USMSConfig usmsconfig = new USMSConfig(new Account(Config.privateKey, Config.publicKey));
    private static DefaultUSMSClient client = new DefaultUSMSClient(usmsconfig);
    private Jedis jedis = RedisUtils.getJedis();
    private JdbcTemplate jdbc = WxCommonTest.jdbcTemplate();

    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("1", "123123");
        map.put("2", "123123");
        map.put("3", "123123");
        map.put("4", "123123");
        log.info(JSON.toJSONString(map));
    }


    /**
     * 申请短信模板
     */
    @Test
    public  void createUSMSTemplate() throws Exception {
        CreateUSMSTemplateParam createUSMSTemplateParam = new CreateUSMSTemplateParam(1,"考试通知","您好,你有一场考试需要参加!");
        CreateUSMSTemplateResult usmsTemplate = client.createUSMSTemplate(createUSMSTemplateParam);
        System.out.println(usmsTemplate);
    }


    /**
     * 发送模板消息
     */
    @Test
    public void SendTemplate() {
        SendUSMSMessageParam sendUSMSMessageParam = new SendUSMSMessageParam(Arrays.asList("17095315205"), "UTA200908BE83F2");
        sendUSMSMessageParam.setTemplateParams(Arrays.asList("123655"));
        sendUSMSMessageParam.setSigContent("【时代光华】");
        sendUSMSMessageParam.setAction("SendUSMSMessage");
        sendUSMSMessageParam.setSigContent("UCloud");
        SendUSMSMessageResult sendUSMSMessageResult = null;
        try {
            sendUSMSMessageResult = client.sendUSMSMessage(sendUSMSMessageParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sendUSMSMessageResult);
    }

    @Test
    public void queryTemplateRegisterStatus() throws Exception {
        QueryUSMSTemplateResult queryUSMSTemplateResult = client.queryUSMSTemplate(new QueryUSMSTemplateParam("UTA2009289565E3"));
        log.info(queryUSMSTemplateResult + "");
    }

}

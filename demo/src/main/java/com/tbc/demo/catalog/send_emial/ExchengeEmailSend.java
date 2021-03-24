package com.tbc.demo.catalog.send_emial;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tbc.demo.utils.ExchangeClient;
import lombok.extern.slf4j.Slf4j;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ExchengeEmailSend {
    public static String strt = "[{\"fileType\":\"mage/jpeg\",\"fileId\":\"5df0573945ce33c0e0cd91d7_0100\",\"fileName\":\"Snipaste_2019-12-11_10-31-09.jpg\",\"signedDownloadUrl\":\"https:/develop1.21tb.com/sf-server/file/downloadFile/83f07a092602380ddef5c658f6b5d322-N_1523333333333/5df0573945ce33c0e0cd91d7_0100\",\"fileSize\":\"18707\"},{\"fileType\":\"mage/jpeg\",\"fileId\":\"5df0573945ce33c0e0cd91d8_0100\",\"fileName\":\"Snipaste_2019-12-11_10-31-24.jpg\",\"signedDownloadUrl\":\"https:/develop1.21tb.com/sf-server/file/downloadFile/1d8cdd8721bdb5392450443f00966347-N_1523333333333/5df0573945ce33c0e0cd91d8_0100\",\"fileSize\":\"11305\"}]";
    public static String url = "http://dev1.test.com/sf-server/file/getFile/ec9b30510e93565a663f6c4053337e6e-S_1331284919850/58a12ae70cf2a3e71434e681_0100";
    public static String str = "{\"fileType\":\"mage/jpeg\",\"fileId\":\"5df0573945ce33c0e0cd91d7_0100\",\"fileName\":\"Snipaste_2019-12-11_10-31-09.jpg\",\"signedDownloadUrl\":\"https:/develop1.21tb.com/sf-server/file/downloadFile/83f07a092602380ddef5c658f6b5d322-N_1523333333333/5df0573945ce33c0e0cd91d7_0100\",\"fileSize\":\"18707\"},{\"fileType\":\"mage/jpeg\",\"fileId\":\"5df0573945ce33c0e0cd91d8_0100\",\"fileName\":\"Snipaste_2019-12-11_10-31-24.jpg\",\"signedDownloadUrl\":\"https:/develop1.21tb.com/sf-server/file/downloadFile/1d8cdd8721bdb5392450443f00966347-N_1523333333333/5df0573945ce33c0e0cd91d8_0100\",\"fileSize\":\"11305\"}";


    public static void main(String[] args) throws Exception {
        ExchangeClient.ExchangeClientImplBuilder sendEntity = new ExchangeClient.ExchangeClientImplBuilder();
        sendEntity.exchangeVersion(ExchangeVersion.Exchange2010_SP2);
        sendEntity.subject("测试邮件请无视");
       // sendEntity.hostname("mail.jiahui.com:587");
        sendEntity.hostname("mail.jiahui.com");
        sendEntity.username("ittest@jiahui.com");
        sendEntity.password("Tbpartners0");
        sendEntity.message("lalalla");
        //sendEntity.recipientTo("ittest@jiahui.com");
        sendEntity.recipientTo("gekangkang@21tb.com");
        //sendEntity.attachments("http://v4.21tb.com/login/newLogin/img/ic_wechat.png");
        ExchangeClient build = sendEntity.build();
        build.sendExchange();
    }



    @Test
    public void stringConvertArray() {
        Object o = msgAttachmentFileRevert(strt);
        System.out.println(o);
    }

    private static Object msgAttachmentFileRevert(String jsonText) {
        jsonText = jsonText.replaceAll("“", "\"").replaceAll("”", "\"");
        if (StringUtils.isNotBlank(jsonText)) {
            try {
                JSONArray objects = JSONObject.parseArray(jsonText);
                Map<Object, Object> map = new HashMap<>();
                map.put("test", objects);
                return map;
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Test
    public void test1(){

    }

}

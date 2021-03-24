package com.tbc.demo.catalog.unionpayLogin;


import com.alibaba.fastjson.JSONObject;
import com.tbc.demo.utils.AESUtils;
import com.tbc.demo.utils.HttpClientUtils;
import com.tbc.demo.utils.RandomValue;
import lombok.extern.slf4j.Slf4j;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


/**
 * 收银学院对接
 */
@Slf4j
@Controller
@RequestMapping("wxsso")
public class WxSSO {

    String v4 = "ecd0f4d2952c4793";
    String v5 = "d5d927788b944b65";
    String yufa = "35add04b28214fa4";
    String cloud = "cd7b7aa788e84c5d";


    /**
     * 注意传入秘钥
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("v4")
    @ResponseBody
    public JSONObject v4SSO() throws Exception {
        String url = "https://v4.21tb.com";
        return test(url, v4);
    }


    @Test
    public void localV4SSO() throws Exception {
        String url = "https://v4.21tb.com";
        test(url, v4);
    }

    @Test
    public void localYufaSSO() throws Exception {
        String url = "https://yufa.21tb.com";
        test(url, yufa);
    }

    @Test
    public void localCloudSSO() throws Exception {
        String url = "http://cloud.21tb.com";
        test(url, cloud);
    }

    @Test
    public void localV5SSO() throws Exception {
        String url = "https://v5.21tb.com";
        test(url, v5);
    }

    @RequestMapping("yufa")
    @ResponseBody
    public JSONObject yufaSSO() throws Exception {
        String url = "https://yufa.21tb.com";
        return test(url, "ac6e41fe2e1f40c7");
    }

    @RequestMapping("cloud")
    @ResponseBody
    public JSONObject cloudSSO() throws Exception {
        String url = "http://cloud.21tb.com";
        return test(url, cloud);
    }


    //首页面
    private String wxSsoLoginIndexPage = "cloud.21tb.com/wxIndex";
    private String wxSsoLoginErrorPage = "cloud.21tb.com/wxIndex";
    private String wxSsoLoginCorpCode = "jinj";
    //    private String wxSsoLoginKey = "b6115be355b44799";
    private String wxSsoLoginKey = "00701d954c2c420c";

    private String decodePassword(String param) {
        if (StringUtils.isEmpty(param)) {
            log.info("param can not be empty");
            return null;
        }
        String[] params;
        try {
            String decode = AESUtils.decode(param, wxSsoLoginKey);
            params = decode.contains("_") ? decode.split("_") : null;
            if (params == null || params.length != 2) {
                log.info("入参格式错误");
                return null;
            }
            String timestamp = params[1];
            long cur = (System.currentTimeMillis() - Long.valueOf(timestamp)) / 1000 / 60;
            if (cur >= 5) {
                log.info("登录超时,请重新登录!");
                return null;
            }
            return params[0].length() == 11 ? params[0] : null;
        } catch (NumberFormatException e) {
            log.info("手机号格式不正确");
            return null;
        } catch (Exception e) {
            log.info("解码失败");
            return null;
        }
    }


    @Test
    public void v5() throws Exception {
        String url = "https://v5.21tb.com";
        test(url, v5);
    }

    @Test
    public void yufa() throws Exception {
        String wxSsoLoginKey = "588b28c673c84139";
        String url = "https://yufa.21tb.com";
        test(url, "588b28c673c84139");
    }

    @Test
    public void cloud() throws Exception {
        String wxSsoLoginKey = "b6115be355b44799";
        String url = "http://cloud.21tb.com";
        test(url, "b6115be355b44799");
    }


/*    public JSONObject test(String url, String wxSsoLoginKey) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String s = AESUtils.encodeAddtimestamp("15601697412", wxSsoLoginKey);
        String str = url + "/biz-oim/wxsso/syxyLogin?ssokey=" + s;
        jsonObject.put("正常登陆: ", str);
        System.out.println(str);
        String s1 = AESUtils.encodeAddtimestamp(RandomValue.getTel(), wxSsoLoginKey);
        String str1 = url + "/biz-oim/wxsso/syxyLogin?ssokey=" + s1;
        jsonObject.put("新增创建: ", str1);
        String s3 = AESUtils.encodeAddtimestamp("137779854", wxSsoLoginKey);
        String str2 = url + "/biz-oim/wxsso/syxyLogin?ssokey=" + s3;
        jsonObject.put("手机错误: ", str2);
        String s4 = AESUtils.encodeAddtimestamp("137779854", "wxSsoLoginKeyaaa");
        String str3 = url + "/biz-oim/wxsso/syxyLogin?ssokey=" + s4;
        jsonObject.put("秘钥错误: ", str3);
        System.out.println(jsonObject);
        return jsonObject;
    }
    */


    public JSONObject test(String url, String wxSsoLoginKey) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String s = AESUtils.encodeAddtimestamp("15601697412", wxSsoLoginKey);
        String str = url + "/biz-oim/wxsso/syxyLogin?ssokey=" + s;
        System.out.println("正常登陆: " + str);
        String s1 = AESUtils.encodeAddtimestamp(RandomValue.getTel(), wxSsoLoginKey);
        String str1 = url + "/biz-oim/wxsso/syxyLogin?ssokey=" + s1;
        System.out.println("新: " + str1);
        String s3 = AESUtils.encodeAddtimestamp("137779854", wxSsoLoginKey);
        String str2 = url + "/biz-oim/wxsso/syxyLogin?ssokey=" + s3;
        System.out.println(str2);
        String s4 = AESUtils.encodeAddtimestamp("137779854", "wxSsoLoginKeyaaa");
        String str3 = url + "/biz-oim/wxsso/syxyLogin?ssokey=" + s4;
        jsonObject.put("秘钥错误: ", str3);
        System.out.println(jsonObject);
        return jsonObject;
    }


    public String createUrl(String url, String wxSsoLoginKey) throws Exception {
        String s = AESUtils.encodeAddtimestamp("15601697412", wxSsoLoginKey);
        return url + "/biz-oim/wxsso/syxyLogin?ssokey=" + s;
    }

    @Test
    public void test1() throws Exception {
        String s = AESUtils.encodeAddtimestamp("13777777777", wxSsoLoginKey);
        System.out.println(s);
        System.out.println(decodePassword(s));
    }


    public void forTest(String url, String key) throws Exception {
        for (int i = 0; i < 10000; i++) {
            String s = AESUtils.encodeAddtimestamp("15601697412", key);
            String str = url + "/biz-oim/wxsso/syxyLogin?ssokey=" + s;
            HttpClientUtils.httpGet(str);
        }
    }


    @Test
    public void forTest1() throws Exception {
//        V4forTest();
        //  V5forTest();
        yufaforTest();
    }

    public void V4forTest() throws Exception {
        String v4url = "https://v4.21tb.com";
        String key = v4;
        forTest(v4url, v4);
    }

    public void V5forTest() throws Exception {
        String v4url = "https://v5.21tb.com";
        forTest(v4url, v5);
    }

    public void yufaforTest() throws Exception {
        String v4url = "https://yufa.21tb.com";
        forTest(v4url, yufa);
    }
}

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

    static String v4 = "ecd0f4d2952c4793";
    static String v5 = "d5d927788b944b65";
    String yufa = "35add04b28214fa4";
    String cloud = "cd7b7aa788e84c5d";

    public static void main(String[] args) throws Exception {
        String url = createUrl("http://v5.21tb.com", v5);
        System.out.println(url);
    }


    //首页面
    private String wxSsoLoginIndexPage = "cloud.21tb.com/wxIndex";
    private String wxSsoLoginErrorPage = "cloud.21tb.com/wxIndex";
    private String wxSsoLoginCorpCode = "jinj";
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


    public static String createUrl(String url, String wxSsoLoginKey) throws Exception {
        String s = AESUtils.encodeAddtimestamp("14718099088", wxSsoLoginKey);
        return url + "/biz-oim/wxsso/syxyLogin?ssokey=" + s;
    }

}

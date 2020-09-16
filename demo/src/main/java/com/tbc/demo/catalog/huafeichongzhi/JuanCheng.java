package com.tbc.demo.catalog.huafeichongzhi;


import com.alibaba.fastjson.JSONObject;
import com.tbc.demo.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * 测试橙卷对接
 */
@Slf4j
public class JuanCheng {


    private static final String appId = "15020190055";
    private static final String sercret = "4408520A2EFEF57CDB4114DBFE26694A";

    public static void main(String[] args) throws UnsupportedEncodingException {
        JSONObject jsonObject = HttpClientUtils.httpGet(getBaseParam("17095315205", "15020190055", "4408520A2EFEF57CDB4114DBFE26694A", "20"));
        System.out.println(jsonObject);
    }

    public static String getBaseParam(String phone, String appId, String sercret, String price) throws UnsupportedEncodingException {
        String orderNo = UUID.randomUUID().toString().replaceAll("-", "");
        String timestamp = System.currentTimeMillis() + "";
        String encode ="http://cloud.21tb.com/wxIndex";
        String baseParam = "app_id=" + appId + "&notify_url=" + encode + "&order_no=" + orderNo + "&price=" + price + "&recharge_number=" + phone + "&timestamp=" + timestamp ;        ;
        String signStr = baseParam + "&key=" + sercret;
        String sign = DigestUtils.md5DigestAsHex(signStr.getBytes()).toUpperCase();
        baseParam = "http://test.api.chengquan.vip:11140/order/tel/pay?" + baseParam + "&sign=" + sign;
        return baseParam;
    }
}

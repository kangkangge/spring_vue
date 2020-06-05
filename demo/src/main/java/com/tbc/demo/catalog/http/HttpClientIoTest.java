package com.tbc.demo.catalog.http;

import com.alibaba.fastjson.JSONObject;
import com.tbc.demo.utils.AESUtils;
import com.tbc.demo.utils.RandomValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;

/**
 * 注释
 *
 * @author gekangkang
 * @date 2019/8/31 11:16
 */
@Slf4j
public class HttpClientIoTest {


    /**
     * http POST 发送JSON数据 注意后端使用 request Body JSON格式的需要使用 post 发送JSON
     *
     */
    @Test
    public void post() {
        //创建客户端对象!
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        //创建请求方式与请求地址
        HttpPost httpPost = new HttpPost("http://localhost:8080/demo/from");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "张三");
        jsonObject.put("password", "kjsdfk");
        //设置请求信息
        StringEntity requestEntity = new StringEntity(jsonObject.toString(), "utf-8");
        requestEntity.setContentEncoding("UTF-8");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setEntity(requestEntity);
        httpPost.setEntity(requestEntity);
        try {
            //响应完整信息
            CloseableHttpResponse execute = httpClient.execute(httpPost);
            //响应数据
            String returnValue = httpClient.execute(httpPost, responseHandler);
            //获取响应状态
            int statusLine = execute.getStatusLine().getStatusCode();
            System.out.println("execute" + execute);
            System.out.println("returnValue" + returnValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws Exception {
        String url = createUrl("https://v5.21tb.com", "2c89599877e84133");
        System.out.println("正常登陆: " + url);
        String url1 = createUrl("https://v5.21tb.com", "2c89599877e84133", RandomValue.getTel());
        System.out.println("新增创建: " + url1);
        String url2 = createUrl("https://v5.21tb.com", "2c89599877e84111");
        System.out.println("错误秘钥: " + url2);
         String url3 = createUrl("https://v5.21tb.com", "2c89599877e84133","12313");
        System.out.println("错误手机号: " + url3);
    }

    private void forSend(String url) throws IOException {
        for (int i = 0; i < 100; i++) {
            CloseableHttpClient aDefault = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse execute = aDefault.execute(httpGet);
            log.info(execute.toString());
        }
    }


    public String createUrl(String url, String wxSsoLoginKey) throws Exception {
        String s = AESUtils.encodeAddtimestamp("13777744441", wxSsoLoginKey);
        return url + "/biz-oim/wxsso/syxyLogin?ssokey=" + s;
    }
    public String createUrl(String url, String wxSsoLoginKey,String phone) throws Exception {
        String s = AESUtils.encodeAddtimestamp(StringUtils.isNotBlank(phone) ? phone : "13777744441", wxSsoLoginKey);
        return url + "/biz-oim/wxsso/syxyLogin?ssokey=" + s;
    }
}

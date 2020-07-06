package com.tbc.demo.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RequestMapping("test")
@Controller
public class SSOTest {


    @ResponseBody
    @RequestMapping("yufaSSo")
    public String yufaSSo() throws UnsupportedEncodingException {
        String loginName = "admin";
        String corpCode = "cscec4b";
        String secret = "cscec4b123";
        String domain = "yufa.21tb.com";
        String password = "Eln000000";
        String returnUrl = "http://yufa.21tb.com/wxIndex/";
        return wxElnSSOLogin(loginName, corpCode, secret, domain, returnUrl);
    }

    @Test
    public void v4SSo() throws UnsupportedEncodingException {
        String loginName = " zhangchunhao";
        String corpCode = "muyi.ge";
        String secret = "mzq123";
        String domain = "v4.21tb.com";
        String password = "Lt.123456";
        String returnUrl = "http://v4.21tb.com/wxIndex/";
        System.out.println("生成的url: " + wxElnSSOLogin(loginName, corpCode, secret, domain, returnUrl));
    }

    /**
     * @param loginName 账号
     * @param corpCode  公司id
     * @param secret    秘钥
     * @param domain    域名
     * @param returnUrl 返回的地址
     * @return 生成的完整地址
     * @throws UnsupportedEncodingException
     */
    private String wxElnSSOLogin(String loginName, String corpCode, String secret, String domain, String returnUrl) throws UnsupportedEncodingException {
        //热力集团elearning的地址
        String eln4SSOUrl = "http://" + domain + "/wx/elpSSO/ssoLogin.do";
        //获取当前时间的时间戳
        long timestamp = System.currentTimeMillis();
        //---------以下代码使用标准MD5算法，结合上面的参数计算出MD5密文----------
        String action = "sso";
        String signingText = secret + "|" + action + "|" + corpCode + "|" + loginName + "|" +
                timestamp + "|" + secret;
        String md5 = DigestUtils.md5Hex(signingText);
        //---------MD5密文计算完毕----------
        //拼接成单点登录的url，只要在页面上点击该URL就可以登录e-learning平台
        String url = eln4SSOUrl + "?userName=" + URLEncoder.encode(loginName, "utf-8") + "&timestamp=" + timestamp + "&corpCode=" +
                corpCode + "&sign=" + md5 + "&functionName=" + URLEncoder.encode(returnUrl, "utf-8");
        System.out.println(signingText);
        //输出登录url
        System.out.println(url);
        return url;
    }
}

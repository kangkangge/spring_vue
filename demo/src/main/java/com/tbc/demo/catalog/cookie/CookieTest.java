package com.tbc.demo.catalog.cookie;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 注释
 *
 * @author gekangkang
 * @date 2019/11/1 14:07
 */
@RestController
@RequestMapping("cookieTest")
public class CookieTest {

    @RequestMapping("setCookie")
    public String setCookie(HttpServletRequest req, HttpServletResponse rep) throws UnsupportedEncodingException {
        String serverName = req.getServerName();
        Cookie coookie = new Cookie("user", "张三");
        String domain1 = getDomain(serverName);
        coookie.setDomain(domain1);
        rep.addCookie(coookie);
        return "成功";
    }

    @RequestMapping("getCookie")
    public String getCookie(HttpServletRequest req, HttpServletResponse rep) throws UnsupportedEncodingException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            String str = URLDecoder.decode(cookie.getValue(), "UTF-8");
            JSONObject jsonObject = JSONObject.parseObject(str);
            System.out.println("test");
        }
        return "成功";
    }

    public static String getDomain(String domain) {
        StringBuffer sb = new StringBuffer();
        sb.append(".");
        sb.append(domain);
        String s = sb.toString();
        return s;
    }
}

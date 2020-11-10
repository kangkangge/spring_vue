package com.tbc.demo.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@RequestMapping("webDemo")
@Controller
@Slf4j
public class WebDemo {

    @RequestMapping("test")
    public void test(@RequestBody JSONObject user) throws IOException {
        log.info("public login createUser JSON: [{}]", user);
        String supplierCode = user.getString("supplierCode");
        String organizeName = user.getString("supplierName");
        String mobile = user.getString("mobile");
        String positionName = user.getString("positionName");
        String userName = user.getString("userName");
        String md5Value = user.getString("md5Value");
        String timestamp = user.getString("timestamp");
        log.info("public login createUser supplierCode: [{}]", supplierCode);
        if (!allNotEmpty(timestamp,md5Value,userName,positionName,timestamp,organizeName,mobile)) {
            System.out.println("1111111111111");
        }
        Pattern compile = Pattern.compile("^1[3-9]{1}\\d{9}$");
        if (!compile.matcher(mobile).find()) {
            System.out.println("2222222");
        }
    }

    @RequestMapping("test1")
    public void test1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("http://cloud.21tb.com");
    }

    public Boolean allNotEmpty(String... str) {
        try {
            for (String s : str) {
                if (StringUtils.isEmpty(s)) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

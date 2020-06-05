package com.tbc.demo;

import com.alibaba.fastjson.JSONObject;
import com.tbc.demo.utils.ContextHolderUtils;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RequestMapping("webTest")
@Controller
@Slf4j
public class WebTest {

    @RequestMapping("demo")
    public void demo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> list = Arrays.asList("999", "111", "222", "999");
        String str = java.net.URLEncoder.encode("这个,是用来测试的!", "UTF-8");
        response.sendRedirect("http://localhost:8080/webTest/demo1?err=" + str);
    }

    public static String test(String userId) {
        for (int i = 0; i < 9999L; i++) {
            System.out.println(userId);
            userId += 1;
        }
        return userId;
    }
    @Test
    public void testphone(){
        boolean b = checkPhone("11997999911");
        System.out.println(b);
    }

    public static boolean checkPhone(String str) {
        Pattern pat = Pattern.compile("^[1][356789][0-9]{9}$");
        Matcher mat = pat.matcher(str);
        return mat.find();
    }
}

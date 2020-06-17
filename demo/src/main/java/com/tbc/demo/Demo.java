package com.tbc.demo;

import com.alibaba.fastjson.JSONObject;
import com.tbc.demo.utils.ThreadsUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Demo {


    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("测试");
        strings.add("测试1");
        ThreadsUtils.autoExecute(new Demo(), "test2", strings);
    }

    public  void test2(List list) {
        System.out.println(JSONObject.toJSONString(list));
    }
}

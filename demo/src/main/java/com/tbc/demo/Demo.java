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
        List<Object> objects = new ArrayList<>(99);
        objects.add(0, "asdf");
        objects.add(1, "asdf1");
        objects.add(2, "asdf2");
        objects.add(3, "asdf2");
        System.out.println(objects.size());
    }

    public void test2(List list) {
        System.out.println(JSONObject.toJSONString(list));
    }
}

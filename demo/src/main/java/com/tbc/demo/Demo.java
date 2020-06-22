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
        int test = 0;

        tag:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                test += 1;
                System.out.println(test);
                if ((i > 0 && j > 2)) {
                    System.out.println("跳出循环!");
                    break tag;
                }
            }
        }
        System.out.println("最外层!");
    }

    public void test2(List list) {
        System.out.println(JSONObject.toJSONString(list));
    }
}

package com.tbc.demo.catalog.asynchronization.utils;

import com.alibaba.fastjson.JSONObject;
import com.tbc.demo.utils.ThreadsUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Demo {

    public static void main(String[] args) throws Exception {
        Demo demo = new Demo();
        ArrayList<com.tbc.demo.entity.User> objects = new ArrayList<>();
        List<com.tbc.demo.entity.User> ojb = ThreadsUtils.getFuture(ThreadsUtils.autoExecute(demo, "test", objects));
        log.info("结果: {}", JSONObject.toJSONString(ojb));
    }

    public List test(List list) {
        com.tbc.demo.entity.User user = new com.tbc.demo.entity.User();
        user.setNickname("测试");
        list.add(user);
        log.info("{}", JSONObject.toJSONString(list));
        return list;
    }


}

package com.tbc.demo.catalog;

import com.tbc.demo.common.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Demo {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(i);
            user.setUsername("张三" + 1);
            users.add(user);
        }
        User user = new User();
        user.setAge(2);
        user.setUsername("张三");
        users.add(user);
        List<String> collect = users.stream().map(USER -> USER.getUsername()).distinct().collect(toList());
        System.out.println(collect);
        List<User> resutl = users.stream().filter(user1 -> user1.getUsername().equals("张三")).collect(toList());
        System.out.println(resutl);
    }

}

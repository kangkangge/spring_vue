package com.tbc.demo.controller;

import com.tbc.demo.dao.xml.UserMapper;
import com.tbc.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.UUID;

@RequestMapping("user")
@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("addUser")
    @ResponseBody
    public User addUser() {
//        int insert = userMapper.insert(getUser());
        int insert = userMapper.insert(getUser());
        return getUser();
    }

    private User getUser() {
        User user = new User();
        user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        user.setNickname("测试");
        user.setPassword("asdfasdf");
        user.setUserEmail("asdfasdf@qq.com");
        return user;
    }
}

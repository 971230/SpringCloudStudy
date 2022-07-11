package com.longj.controller;

import com.longj.entity.User;
import com.longj.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 22:02
 * @Version 1.0
 */
@RestController
public class UserController {

    @Resource
    UserService service;

    /**
     * 这里以RESTFul风格为例
     *
     * @param uid uid
     * @return User 用户信息
     */
    @RequestMapping("/user/{uid}")
    public User findUserById(@PathVariable("uid") int uid) {
        return service.getUserById(uid);
    }
}

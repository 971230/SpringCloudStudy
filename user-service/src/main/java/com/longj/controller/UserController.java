package com.longj.controller;

import com.longj.entity.User;
import com.longj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@Slf4j
@RefreshScope // 可以让nacos中的配置文件一旦变化就可以立即监听到变化并生效,热加载
public class UserController {

    @Resource
    UserService service;

    @Value("test.lbwnb")
    String update;

    /**
     * 这里以RESTFul风格为例
     *
     * @param uid uid
     * @return User 用户信息
     */
    @RequestMapping("/user/{uid}")
    public User findUserById(@PathVariable("uid") int uid) {
        log.info("调用user-service...");
        log.info(update);
        return service.getUserById(uid);
    }
}

package com.longj.client;

import com.longj.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author 龙江锋
 * @Date 2022/7/13 21:14
 * @Version 1.0
 */
@Component // 注意,需要被spring容器管理,需要将其注册为Bean，Feign才能自动注入
@Slf4j
public class UserFallbackClient implements UserClient {

    /**
     * 去实现OpenFeign负载均衡的接口,实现的方法就是对应的服务挂掉后的降级的操作
     * 记得去配置文件添加相应熔断配置
     */
    @Override
    public User getUserById(int uid) {
        User user = new User();
        user.setName("暂时没有访问到用户");
        user.setSex("未知性别");
        user.setUid(-1);
        log.info("user服务出现问题,已降级(熔断),返回默认信息");
        return user;
    }
}

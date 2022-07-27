package com.longj.service.client;

import com.longj.entity.User;
import org.springframework.stereotype.Component;

/**
 * @Author 龙江锋
 * @Date 2022/7/27 20:59
 * @Version 1.0
 */
@Component
public class UserClientImpl implements UserClient{
    @Override
    public User getUserById(int uid) {
        User user = new User();
        user.setName("我是在用户服务挂掉后的替代方案");
        return user;
    }
}

package com.longj.service;

import com.longj.entity.User;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 21:57
 * @Version 1.0
 */
public interface UserService {
    /**
     * 通过ID获取用户信息
     *
     * @param uid uid
     * @return User 用户实体类
     */
    User getUserById(int uid);

    int getRemain(int uid);

    boolean setRemain(int uid, int count);
}


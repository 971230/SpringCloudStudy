package com.longj.service;

import com.longj.entity.User;
import com.longj.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 21:58
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper mapper;

    @Override
    public User getUserById(int uid) {
        return mapper.getUserById(uid);
    }

    @Override
    public int getRemain(int uid) {
        return mapper.getUserBookRemain(uid);
    }

    @Override
    public boolean setRemain(int uid, int count) {
        return mapper.updateBookCount(uid, count) > 0;
    }
}

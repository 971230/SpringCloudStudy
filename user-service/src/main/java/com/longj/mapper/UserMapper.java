package com.longj.mapper;

import com.longj.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 21:59
 * @Version 1.0
 */
@Mapper
public interface UserMapper {
    /**
     * 通过ID获取用户信息
     *
     * @param uid uid
     * @return User 用户实体类
     */
    @Select("select * from DB_USER where uid = #{uid}")
    User getUserById(int uid);
}

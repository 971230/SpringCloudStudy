package com.longj.mapper;

import com.longj.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 查询书籍剩余量
     *
     * @param uid uid
     * @return User 用户实体类
     */
    @Select("select book_count from DB_USER where uid = #{uid}")
    int getUserBookRemain(int uid);

    /**
     * 更新剩余书籍量
     *
     * @param uid uid
     * @param count 剩余量
     * @return User 用户实体类
     */
    @Update("update DB_USER set book_count = #{count} where uid = #{uid}")
    int updateBookCount(int uid, int count);
}

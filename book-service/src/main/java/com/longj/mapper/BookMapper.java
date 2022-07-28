package com.longj.mapper;

import com.longj.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 22:05
 * @Version 1.0
 */
@Mapper
public interface BookMapper {

    @Select("select * from DB_BOOK where bid = #{bid}")
    Book getBookById(int bid);

    @Select("select count from DB_BOOK  where bid = #{bid}")
    int getRemain(int bid);

    @Update("update DB_BOOK set count = #{count}  where bid = #{bid}")
    int setRemain(int bid, int count);
}

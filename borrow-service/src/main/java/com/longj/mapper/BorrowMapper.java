package com.longj.mapper;

import com.longj.entity.Borrow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 22:09
 * @Version 1.0
 */
@Mapper
public interface BorrowMapper {
    @Select("select * from DB_BORROW where uid = #{uid}")
    List<Borrow> getBorrowsByUid(int uid);

    @Select("select * from DB_BORROW where bid = #{bid}")
    List<Borrow> getBorrowsByBid(int bid);

    @Select("select * from DB_BORROW where bid = #{bid} and uid = #{uid}")
    Borrow getBorrow(int uid, int bid);

    @Insert("insert into DB_BORROW(uid, bid) values(#{uid},#{bid})")
    int addBorrow(int uid, int bid);
}

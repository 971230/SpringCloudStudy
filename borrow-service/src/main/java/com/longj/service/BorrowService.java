package com.longj.service;

import com.longj.entity.BorrowDetail;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 22:20
 * @Version 1.0
 */
public interface BorrowService {
    BorrowDetail getUserBorrowDetailByUid(int uid);

    /**
     * 借阅接口
     * @param uid 借阅书籍的用户id
     * @param bid 借阅的书籍id
     * @return boolean 是否借阅成功
     */
    boolean doBorrow(int uid, int bid);
}

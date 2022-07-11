package com.longj.service;

import com.longj.entity.BorrowDetail;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 22:20
 * @Version 1.0
 */
public interface BorrowService {
    BorrowDetail getUserBorrowDetailByUid(int uid);
}

package com.longj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 22:20
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class BorrowDetail {
    User user;
    List<Book> bookList;
}

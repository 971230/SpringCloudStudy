package com.longj.service;

import com.longj.entity.Book;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 22:05
 * @Version 1.0
 */
public interface BookService {
    Book getBookById(int bid);

    boolean setRemain(int bid, int count);

    int getRemain(int bid);
}

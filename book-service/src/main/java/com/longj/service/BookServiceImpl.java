package com.longj.service;

import com.longj.entity.Book;
import com.longj.mapper.BookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 22:06
 * @Version 1.0
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper mapper;

    @Override
    public Book getBookById(int bid) {
        return mapper.getBookById(bid);
    }
}

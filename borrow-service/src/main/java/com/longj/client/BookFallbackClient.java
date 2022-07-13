package com.longj.client;

import com.longj.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author 龙江锋
 * @Date 2022/7/13 21:30
 * @Version 1.0
 */
@Component
@Slf4j
public class BookFallbackClient implements BookClient{
    @Override
    public Book findBookById(int bid) {
        Book book = new Book();
        book.setBid(-1);
        book.setTitle("暂未访问到书籍");
        book.setDesc("没有书籍描述");
        log.info("book服务出现问题,已降级(熔断),返回默认信息");
        return book;
    }
}

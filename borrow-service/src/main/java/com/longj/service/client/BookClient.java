package com.longj.service.client;

import com.longj.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 龙江锋
 * @Date 2022/7/19 20:35
 * @Version 1.0
 */
@FeignClient("book-service")
public interface BookClient {
    @RequestMapping("/book/{bid}")
    Book getBookById(@PathVariable("bid") int bid);

    @RequestMapping("/book/borrow/{bid}")
    boolean bookBorrow(@PathVariable("bid") int bid);

    @RequestMapping("/book/remain/{bid}")
    int bookRemain(@PathVariable("bid") int bid);
}

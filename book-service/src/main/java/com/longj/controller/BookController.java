package com.longj.controller;

import com.longj.entity.Book;
import com.longj.service.BookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 22:07
 * @Version 1.0
 */
@RestController
public class BookController {

    @Resource
    BookService service;

    @RequestMapping("/book/{bid}")
    Book findBookById(@PathVariable("bid") int bid, HttpServletRequest servletRequest) {
        // 测试 borrowservice 经过gateway转发过来后添加的请求头是否添加成功
        System.out.println(servletRequest.getHeader("Test"));
        return service.getBookById(bid);
    }
}

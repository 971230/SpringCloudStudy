package com.longj.controller;

import com.longj.entity.Book;
import com.longj.service.BookService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 22:07
 * @Version 1.0
 */
@RestController
@Slf4j
public class BookController {

    @Resource
    BookService service;

    @RequestMapping("/book/{bid}")
    Book findBookById(@PathVariable("bid") int bid){
        log.info("调用book-service...");
        return service.getBookById(bid);
    }

    @RequestMapping("/book/remain/{bid}")
    public int bookRemain(@PathVariable("bid") int uid){
        // 这里打印一下XID看看，其他的服务业添加这样一个打印，如果一会都打印的是同一个XID，表示使用的就是同一个事务
        System.out.println(RootContext.getXID());
        return service.getRemain(uid);
    }

    @RequestMapping("/book/borrow/{bid}")
    public boolean bookBorrow(@PathVariable("bid") int uid){
        int remain = service.getRemain(uid);
        return service.setRemain(uid, remain - 1);
    }
}

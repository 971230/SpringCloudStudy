package com.test.controller;

import com.test.entity.Book;
import com.test.service.BookService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author long
 */
@RestController
public class BookController {

    @Resource
    BookService service;

    @RequestMapping("/book/{bid}")
    Book findBookById(@PathVariable("bid") int bid, HttpSession session) {
        //通过SecurityContextHolder将用户信息取出
        SecurityContext context = SecurityContextHolder.getContext();
        SecurityContext springSecurityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        System.out.println(springSecurityContext == null); // true 作为资源服务器HttpSession不保存SPRING_SECURITY_CONTEXT
        System.out.println("验证信息:" + context.getAuthentication());
        return service.getBookById(bid);
    }
}
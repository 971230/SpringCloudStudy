package com.longj.controller;

import com.longj.entity.BorrowDetail;
import com.longj.service.BorrowService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 22:38
 * @Version 1.0
 */
@RestController
public class BorrowController {

    @Resource
    BorrowService service;

    @RequestMapping("/borrow/{uid}")
    BorrowDetail findUserBorrows(@PathVariable("uid") int uid){
        return service.getUserBorrowDetailByUid(uid);
    }

    @RequestMapping("/borrow1/{uid}")
    BorrowDetail findUserBorrows1(@PathVariable("uid") int uid){
        return service.getUserBorrowDetailByUid(uid);
    }
}

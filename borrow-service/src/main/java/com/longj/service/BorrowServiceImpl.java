package com.longj.service;

import com.longj.entity.Book;
import com.longj.entity.Borrow;
import com.longj.entity.User;
import com.longj.entity.BorrowDetail;
import com.longj.mapper.BorrowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 22:30
 * @Version 1.0
 */
@Service
public class BorrowServiceImpl implements BorrowService {

    @Resource
    BorrowMapper mapper;

    @Resource
    RestTemplate template;

    /**
     * 添加eureka注册服务发现，地址可以替换成对应的服务名称，对应的RestTemplate对象换成注入形式
     *
     * @param uid uid
     * @return BorrowDetail 借阅细节
     */
    @Override
    public BorrowDetail getUserBorrowDetailByUid(int uid) {
        List<Borrow> borrow = mapper.getBorrowsByUid(uid);

        //这里不用再写IP，直接写服务名称userservice
        User user = template.getForObject("http://userservice/user/" + uid, User.class);
        //这里不用再写IP，直接写服务名称bookservice
        List<Book> bookList = borrow
                .stream()
                .map(b -> template.getForObject("http://bookservice/book/" + b.getBid(), Book.class))
                .collect(Collectors.toList());
        return new BorrowDetail(user, bookList);
    }

    /**
     * 未添加eureka注册服务发现之前的写法
     */
    private BorrowDetail getUserBorrowDetailByUidbefore(int uid) {
        List<Borrow> borrow = mapper.getBorrowsByUid(uid);
        //那么问题来了，现在拿到借阅关联信息了，怎么调用其他服务获取信息呢？
        //RestTemplate支持多种方式的远程调用
        RestTemplate template = new RestTemplate();
        //这里通过调用getForObject来请求其他服务，并将结果自动进行封装
        //获取User信息
        User user = template.getForObject("http://userservice/user/" + uid, User.class);
        //获取每一本书的详细信息
        List<Book> bookList = borrow
                .stream()
                .map(b -> template.getForObject("http://bookservice/book/" + b.getBid(), Book.class))
                .collect(Collectors.toList());
        return new BorrowDetail(user, bookList);
    }
}

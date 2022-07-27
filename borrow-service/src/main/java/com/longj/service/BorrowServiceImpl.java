package com.longj.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.longj.entity.Book;
import com.longj.entity.Borrow;
import com.longj.entity.User;
import com.longj.entity.BorrowDetail;
import com.longj.mapper.BorrowMapper;
import com.longj.service.client.BookClient;
import com.longj.service.client.UserClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
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
    UserClient userClient;

    @Resource
    BookClient bookClient;

    /**
     * @SentinelResource 精准限流:监控此方法，无论被谁执行都在监控范围内，这里给的value是自定义名称，这个注解可以加在任何方法上，
     * 包括Controller中的请求映射方法，跟HystrixCommand贼像
     * blockHandler参数可以提供限流后执行具体哪一个方法,具体到方法
     */
    @SentinelResource(value = "details", blockHandler = "blocked")
    @Override
    public BorrowDetail getUserBorrowDetailByUid(int uid) {
        List<Borrow> borrow = mapper.getBorrowsByUid(uid);
        //那么问题来了，现在拿到借阅关联信息了，怎么调用其他服务获取信息呢？

        //这里通过调用getForObject来请求其他服务，并将结果自动进行封装
        //获取User信息
        User user = userClient.getUserById(uid);
        //获取每一本书的详细信息
        List<Book> bookList = borrow
                .stream()
                .map(b -> bookClient.getBookById(b.getBid()))
                .collect(Collectors.toList());
        return new BorrowDetail(user, bookList);
    }

    /**
     * 替代方案，注意参数和返回值需要保持一致，并且参数最后还需要额外添加一个BlockException
     */
    public BorrowDetail blocked(int uid, BlockException e) {
        return new BorrowDetail(null, Collections.emptyList());
    }
}

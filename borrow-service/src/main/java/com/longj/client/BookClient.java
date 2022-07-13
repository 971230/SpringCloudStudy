package com.longj.client;

import com.longj.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 龙江锋
 * @Date 2022/7/13 20:03
 * @Version 1.0
 */
@FeignClient(value = "bookservice", fallback = BookFallbackClient.class)
public interface BookClient {
    /**
     * 书籍服务
     *
     * @param bid bid
     * @return Book 书籍信息
     */
    @RequestMapping("/book/{bid}")
    Book findBookById(@PathVariable("bid") int bid);
}

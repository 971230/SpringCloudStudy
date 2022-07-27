package com.longj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 21:56
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    int uid;
    String name;
    String sex;
}

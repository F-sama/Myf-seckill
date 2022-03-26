package com.myf.seckill.utils;

import java.util.UUID;

/**
 * @program: Myf-seckill
 * @description: 生成UUID
 * @author: Yunfei Ma
 * @email: fsama@mail.ustc.edu.cn
 * @create: 2022-03-22 20:13
 **/
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

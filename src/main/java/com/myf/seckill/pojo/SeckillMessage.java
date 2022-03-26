package com.myf.seckill.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: Myf-seckill
 * @description: 秒杀信息
 * @author: Yunfei Ma
 * @email: fsama@mail.ustc.edu.cn
 * @create: 2022-03-25 18:02
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeckillMessage {
    private User user;
    private Long goodsId;
}
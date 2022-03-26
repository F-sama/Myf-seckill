package com.myf.seckill.vo;

import com.myf.seckill.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: Myf-seckill
 * @description: 商品详情返回对象
 * @author: Yunfei Ma
 * @email: fsama@mail.ustc.edu.cn
 * @create: 2022-03-24 17:44
 **/


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailVo {


    private User user;

    private GoodsVo goodsVo;

    private int seckillStatus;

    private int remainSeconds;



}

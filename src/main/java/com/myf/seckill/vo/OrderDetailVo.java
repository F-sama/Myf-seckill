package com.myf.seckill.vo;

import com.myf.seckill.pojo.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: Myf-seckill
 * @description: 订单详情返回对象
 * @author: Yunfei Ma
 * @email: fsama@mail.ustc.edu.cn
 * @create: 2022-03-24 22:30
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailVo {
    private Order order;
    private GoodsVo goodsVo;

}

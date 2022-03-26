package com.myf.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myf.seckill.pojo.SeckillOrder;
import com.myf.seckill.pojo.User;

/**
 * <p>
 * 秒杀订单表 服务类
 * </p>
 *
 * @author Yunfei Ma
 * @since 2022-03-23
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {

    Long getResult(User user, Long goodsId);
}

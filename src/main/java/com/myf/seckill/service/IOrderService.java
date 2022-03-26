package com.myf.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myf.seckill.pojo.Order;
import com.myf.seckill.pojo.User;
import com.myf.seckill.vo.GoodsVo;
import com.myf.seckill.vo.OrderDetailVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yunfei Ma
 * @since 2022-03-23
 */
public interface IOrderService extends IService<Order> {
    /** 
    * @Description: 秒杀
    * @Param: 
    * @return: com.myf.seckill.pojo.Order
    * @Author: Yunfei Ma
    * @Date: 2022/3/23
    */
    Order seckill(User user, GoodsVo goods);

    OrderDetailVo detail(Long orderId);

    String createPath(User user, Long goodsId);

    boolean checkPath(User user, Long goodsId, String path);
}

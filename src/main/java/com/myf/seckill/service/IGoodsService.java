package com.myf.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myf.seckill.pojo.Goods;
import com.myf.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author Yunfei Ma
 * @since 2022-03-23
 */
public interface IGoodsService extends IService<Goods> {

    List<GoodsVo> findGoodsVo();
    /**
    * @Description: 跳转商品详情页
    * @Param:
    * @return: java.lang.String
    * @Author: Yunfei Ma
    * @Date: 2022/3/23
    */
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}

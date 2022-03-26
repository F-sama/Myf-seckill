package com.myf.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myf.seckill.pojo.Goods;
import com.myf.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author Yunfei Ma
 * @since 2022-03-23
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    /** 
    * @Description: 获取商品列表
    * @Param: 
    * @return: java.util.List<com.myf.seckill.vo.GoodsVo>
    * @Author: Yunfei Ma
    * @Date: 2022/3/23
    */
    List<GoodsVo> findGoodsVo();
    
    
    /** 
    * @Description: 获取商品详情
    * @Param: 
    * @return: java.lang.String
    * @Author: Yunfei Ma
    * @Date: 2022/3/23
    */
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}

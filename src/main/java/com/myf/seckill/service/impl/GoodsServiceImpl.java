package com.myf.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myf.seckill.mapper.GoodsMapper;
import com.myf.seckill.pojo.Goods;
import com.myf.seckill.service.IGoodsService;
import com.myf.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author Yunfei Ma
 * @since 2022-03-23
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    /**
    * @Description: 获取商品列表
    * @Param:
    * @return: java.util.List<com.myf.seckill.vo.GoodsVo>
    * @Author: Yunfei Ma
    * @Date: 2022/3/23
    */
    @Override
    public List<GoodsVo> findGoodsVo() {
        return goodsMapper.findGoodsVo();
    }
    /** 
    * @Description: 获取商品详情
    * @Param: 
    * @return: java.lang.String
    * @Author: Yunfei Ma
    * @Date: 2022/3/23
    */
    @Override
    public GoodsVo findGoodsVoByGoodsId(Long goodsId) {
        return goodsMapper.findGoodsVoByGoodsId(goodsId);
    }
}

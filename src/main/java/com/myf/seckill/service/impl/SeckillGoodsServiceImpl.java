package com.myf.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myf.seckill.mapper.SeckillGoodsMapper;
import com.myf.seckill.pojo.SeckillGoods;
import com.myf.seckill.service.ISeckillGoodsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 秒杀商品表 服务实现类
 * </p>
 *
 * @author Yunfei Ma
 * @since 2022-03-23
 */
@Service
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements ISeckillGoodsService {

}

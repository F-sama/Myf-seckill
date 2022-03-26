package com.myf.seckill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myf.seckill.pojo.Order;
import com.myf.seckill.pojo.SeckillMessage;
import com.myf.seckill.pojo.SeckillOrder;
import com.myf.seckill.pojo.User;
import com.myf.seckill.rabbitmq.MQSender;
import com.myf.seckill.service.IGoodsService;
import com.myf.seckill.service.IOrderService;
import com.myf.seckill.service.ISeckillOrderService;
import com.myf.seckill.service.IUserService;
import com.myf.seckill.utils.JsonUtil;
import com.myf.seckill.vo.GoodsVo;
import com.myf.seckill.vo.RespBean;
import com.myf.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: Myf-seckill
 * @description: 秒杀
 * @author: Yunfei Ma
 * @email: fsama@mail.ustc.edu.cn
 * @create: 2022-03-23 22:56
 **/

@Controller
@RequestMapping("/seckill")
public class SeckillController implements InitializingBean {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISeckillOrderService seckillOrderService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IUserService userService;
    @Autowired
    private MQSender mqSender;

    private Map<Long, Boolean> EmptyStockMap = new HashMap<>();

    @RequestMapping(value = "/doSeckill", method = RequestMethod.POST)
    @ResponseBody
    public RespBean doSeckill(Long goodsId) {

        String uuid = "user:" + userService.getUuid();
        String str = redisTemplate.opsForValue().get(uuid) + "";
        User user = JsonUtil.jsonStr2Object(str, User.class);
        if (user == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }

        ValueOperations valueOperations = redisTemplate.opsForValue();
//        boolean check = orderService.checkPath(user,goodsId,path);
//        if (!check){
//            return RespBean.error(RespBeanEnum.REQUEST_ILLEGAL);
//        }
        //判断是否重复抢购
        String seckillOrderJson = (String) valueOperations.get("order:" +
                user.getId() + ":" + goodsId);
        if (!StringUtils.isEmpty(seckillOrderJson)) {
            return RespBean.error(RespBeanEnum.REPEATE_ERROR);
        }
        //内存标记,减少Redis访问
        if (EmptyStockMap.get(goodsId)) {
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }
        //预减库存
        Long stock = valueOperations.decrement("seckillGoods:" + goodsId);
        if (stock < 0) {
            EmptyStockMap.put(goodsId,true);
            valueOperations.increment("seckillGoods:" + goodsId);
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }
        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
        // 请求入队，立即返回排队中
        SeckillMessage message = new SeckillMessage(user, goodsId);
        mqSender.sendsecKillMessage(JsonUtil.object2JsonStr(message));
        Order order = orderService.seckill(user, goods);

        Long orderId = seckillOrderService.getResult(user, goodsId);
        if (orderId < 0) {
            return RespBean.error(RespBeanEnum.SECKILL_FAILURE);
        } else {
            while (orderId == 0) {
                orderId = seckillOrderService.getResult(user, goodsId);
            }
        }

        return RespBean.success(order);

//
//        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
//        if (goods.getStockCount() < 1) {
//            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
//            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
//        }
//        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId()).eq("goods_id", goodsId));
//        if (seckillOrder != null) {
//            model.addAttribute("errmsg", RespBeanEnum.REPEATE_ERROR.getMessage());
//            return RespBean.error(RespBeanEnum.REPEATE_ERROR);
//        }
//        Order order = orderService.seckill(user, goods);
//        return RespBean.success(order);
    }


    @RequestMapping(value = "/result", method = RequestMethod.GET)
    @ResponseBody
    public RespBean getResult(User user, Long goodsId) {
        if (user == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        Long orderId = seckillOrderService.getResult(user, goodsId);
        return RespBean.success(orderId);
    }


    @RequestMapping(value = "/path", method = RequestMethod.GET)
    @ResponseBody
    public RespBean getPath(User user, Long goodsId) {
        if (user == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }

        String str = orderService.createPath(user, goodsId);
        return RespBean.success(str);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> list = goodsService.findGoodsVo();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        list.forEach(goodsVo -> {
            redisTemplate.opsForValue().set("seckillGoods:" + goodsVo.getId(), goodsVo.getStockCount());
            EmptyStockMap.put(goodsVo.getId(), false);
        });
    }
}

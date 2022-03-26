package com.myf.seckill.controller;


import com.myf.seckill.pojo.User;
import com.myf.seckill.rabbitmq.MQSender;
import com.myf.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Yunfei Ma
 * @since 2022-03-22
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MQSender mqSender;

    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user) {return RespBean.success(user);}


//    /**
//     * 测试发送RabbitMQ消息
//     */
//    @RequestMapping("/mq")
//    @ResponseBody
//    public void mq() {
//        mqSender.send("Hello");
//    }
//
//    /**
//     * fanout模式测试发送RabbitMQ消息
//     */
//    @RequestMapping("/mq/fanout")
//    @ResponseBody
//    public void mq01() {
//        mqSender.send("Hello");
//    }
//
//    /**
//    * @Description: direct模式测试发送rabbitMQ消息
//    * @Param:
//    * @return: void
//    * @Author: Yunfei Ma
//    * @Date: 2022/3/25
//    */
//    @RequestMapping("/mq/direct01")
//    @ResponseBody
//    public void mq02() {
//        mqSender.send01("Hello,Red");
//    }
//
//    @RequestMapping("/mq/direct02")
//    @ResponseBody
//    public void mq03() {
//        mqSender.send02("Hello,Green");
//    }
//
//    /**
//    * @Description: topic模式
//    * @Param:
//    * @return: void
//    * @Author: Yunfei Ma
//    * @Date: 2022/3/25
//    */
//    @RequestMapping("/mq/topic01")
//    @ResponseBody
//    public void mq04() {
//        mqSender.send01("Hello,Red");
//    }
//    /**
//     * 测试发送RabbitMQ消息
//     */
//    @RequestMapping("/mq/topic02")
//    @ResponseBody
//    public void mq05() {
//        mqSender.send02("Hello,Green");
//    }

}

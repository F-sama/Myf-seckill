package com.myf.seckill.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: Myf-seckill
 * @description: 消息发送者
 * @author: Yunfei Ma
 * @email: fsama@mail.ustc.edu.cn
 * @create: 2022-03-25 16:13
 **/
@Service
@Slf4j
public class MQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

//    /**
//    * @Description: fanout模式发送消息
//    * @Param:
//    * @return: void
//    * @Author: Yunfei Ma
//    * @Date: 2022/3/25
//    */
//    public void send(Object msg) {
//        log.info("发送消息："+msg);
//        rabbitTemplate.convertAndSend("fanoutExchange", "",  msg);
//    }
//
//    /**
//    * @Description: direct模式发送消息
//    * @Param:
//    * @return: void
//    * @Author: Yunfei Ma
//    * @Date: 2022/3/25
//    */
//    public void send01(Object msg) {
//        log.info("发送red消息："+msg);
//        rabbitTemplate.convertAndSend("directExchange","queue.red",msg);
//    }
//    public void send02(Object msg) {
//        log.info("发送green消息："+msg);
//        rabbitTemplate.convertAndSend("directExchange","queue.green",msg);
//    }
//
//    /**
//    * @Description: topic模式发送消息
//    * @Param:
//    * @return: void
//    * @Author: Yunfei Ma
//    * @Date: 2022/3/25
//    */
//    public void send03(Object msg) {
//        log.info("发送消息(被01队列接受)："+msg);
//        rabbitTemplate.convertAndSend("topicExchange","queue.red.message",msg);
//    }
//    public void send04(Object msg) {
//        log.info("发送消息(被两个queue接受)："+msg);
//
//        rabbitTemplate.convertAndSend("topicExchange","message.queue.green.abc",msg);
//    }


    /** 
    * @Description: 发送秒杀信息
    * @Param: 
    * @return: void
    * @Author: Yunfei Ma
    * @Date: 2022/3/25
    */
    public void sendsecKillMessage(String message) {
        log.info("发送消息：" + message);
        rabbitTemplate.convertAndSend("seckillExchange", "seckill.msg", message);
    }
}
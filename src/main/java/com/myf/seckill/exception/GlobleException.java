package com.myf.seckill.exception;

import com.myf.seckill.vo.RespBean;
import com.myf.seckill.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: Myf-seckill
 * @description: 全局异常
 * @author: Yunfei Ma
 * @email: fsama@mail.ustc.cn
 * @create: 2022-03-22 18:57
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobleException extends RuntimeException{
    private RespBeanEnum respBeanEnum;

}

package com.myf.seckill.exception;

import com.myf.seckill.vo.RespBean;
import com.myf.seckill.vo.RespBeanEnum;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



/**
 * @program: Myf-seckill
 * @description: 全局异常处理类
 * @author: Yunfei Ma
 * @email: fsama@mail.ustc.edu.cn
 * @create: 2022-03-22 18:58
 **/
@RestControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RespBean ExceptionHandler(Exception e) {
        if (e instanceof GlobleException) {
            GlobleException ex = (GlobleException) e;
            return RespBean.error(ex.getRespBeanEnum());
        }
        else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            RespBean respBean = RespBean.error(RespBeanEnum.BIND_ERROR);
            respBean.setMessage("参数校验异常" + ex.getBindingResult().getAllErrors().get(0));
            return respBean;
        }
        return RespBean.error(RespBeanEnum.ERROR);
    }
    
}

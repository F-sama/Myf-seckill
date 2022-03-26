package com.myf.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myf.seckill.pojo.User;
import com.myf.seckill.vo.LoginVo;
import com.myf.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Yunfei Ma
 * @since 2022-03-22
 */
public interface IUserService extends IService<User> {

    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);
    /** 
    * @Description: 根据cookie获取用户
    * @Param: 
    * @return: com.myf.seckill.pojo.User
    * @Author: Yunfei Ma
    * @Date: 2022/3/23
    */
    User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response);

    String getUuid();
}

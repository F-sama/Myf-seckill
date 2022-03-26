package com.myf.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myf.seckill.exception.GlobleException;
import com.myf.seckill.mapper.UserMapper;
import com.myf.seckill.pojo.User;
import com.myf.seckill.service.IUserService;
import com.myf.seckill.utils.CookieUtil;
import com.myf.seckill.utils.JsonUtil;
import com.myf.seckill.utils.MD5Util;
import com.myf.seckill.utils.UUIDUtil;
import com.myf.seckill.vo.LoginVo;
import com.myf.seckill.vo.RespBean;
import com.myf.seckill.vo.RespBeanEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Yunfei Ma
 * @since 2022-03-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    private String uuid;

    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
//        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
//            return RespBean.error(RespBeanEnum.Login_Error);
//        }
//
//        if (!ValidatorUntil.isMobile(mobile)) {
//            return RespBean.error(RespBeanEnum.Mobile_Error);
//        }

        //根据手机号获取用户
        User user = userMapper.selectById(mobile);
        if (null == user) {
            throw new GlobleException(RespBeanEnum.LOGIN_ERROR);
        }
        //判断用户名的密码是否正确
        if (!MD5Util.formPassToDBPass(password, user.getSalt()).equals(user.getPassword())) {
            throw new GlobleException(RespBeanEnum.LOGIN_ERROR);
        }
        //生成cookie
        String userTicket = UUIDUtil.uuid();
//        request.getSession().setAttribute(ticket, user);
        redisTemplate.opsForValue().set("user:" + userTicket, JsonUtil.object2JsonStr(user));
        this.uuid = userTicket;
        CookieUtil.setCookie(request, response, "userTicket", userTicket);
        return RespBean.success(userTicket);
    }

    @Override
    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(userTicket)) {
            return null;
        }
        String userJson = (String) redisTemplate.opsForValue().get("user:" + userTicket);
        User user = JsonUtil.jsonStr2Object(userJson, User.class);
        if (user != null) {
            CookieUtil.setCookie(request, response, "userTicket", userTicket);
        }
        return user;
    }

    @Override
    public String getUuid() {
        return uuid;
    }


}

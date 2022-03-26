package com.myf.seckill.vo;

import com.myf.seckill.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author Yunfei Ma
 * @create 2022-03-22 16:07
 */

@Data
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;


    @NotNull
    @Length(min = 32)
    private String password;
}

package com.myf.seckill.vo;

import com.myf.seckill.utils.ValidatorUntil;
import com.myf.seckill.validator.IsMobile;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @program: Myf-seckill
 * @description: 手机号码校验规则
 * @author: Yunfei Ma
 * @email: fsama@mail.ustc.edu.cn
 * @create: 2022-03-22 18:36
 **/
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (required) {
            return ValidatorUntil.isMobile(s);
        }
        else {
            if (StringUtils.isEmpty(s)) {
                return true;
            }
            else {
                return ValidatorUntil.isMobile(s);
            }
        }
    }
}

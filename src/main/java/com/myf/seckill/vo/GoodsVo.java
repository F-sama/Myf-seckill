package com.myf.seckill.vo;

import com.myf.seckill.pojo.Goods;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @program: Myf-seckill
 * @description: 商品返回对象
 * @author: Yunfei Ma
 * @email: fsama@mail.ustc.edu.cn
 * @create: 2022-03-23 14:12
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo extends Goods {

    @ApiModelProperty("秒杀价格")
    private BigDecimal seckillPrice;

    @ApiModelProperty("库存数量")
    private Integer stockCount;

    @ApiModelProperty("秒杀开始时间")
    private Date startDate;

    @ApiModelProperty("秒杀结束时间")
    private Date endDate;
}

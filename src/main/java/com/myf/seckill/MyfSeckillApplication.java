package com.myf.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.myf.seckill.mapper")
public class MyfSeckillApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyfSeckillApplication.class, args);
    }

}

package com.shuouo.cointransfer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shuouo.cointransfer.mapper")
public class CointransferApplication {

    public static void main(String[] args) {
        SpringApplication.run(CointransferApplication.class, args);
    }

}

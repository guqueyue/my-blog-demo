package com.guqueyue.myTransactional;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guqueyue
 */
@SpringBootApplication
@MapperScan(value = {"com.guqueyue.myTransactional.dao"})
public class MyTransactionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyTransactionalApplication.class, args);
    }

}

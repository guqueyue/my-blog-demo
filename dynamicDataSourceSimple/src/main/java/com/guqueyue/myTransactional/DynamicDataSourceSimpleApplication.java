package com.guqueyue.myTransactional;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guqueyue
 */
@SpringBootApplication
@MapperScan(value = {"com.guqueyue.dynamicDataSourceSimple.dao"})
public class DynamicDataSourceSimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDataSourceSimpleApplication.class, args);
    }

}

package com.guqueyue.dynamicdatasourcetest.annotation;

import java.lang.annotation.*;

/**
 * @Author: guqueyue
 * @Description: 自定义注解，用以切换数据源
 * @Date: 2023/12/25
 **/
@Target({ElementType.METHOD}) // 注解使用范围为：方法
@Retention(RetentionPolicy.RUNTIME) // 注解的生命周期为运行时
@Documented
public @interface MyDataSource {

//    DataSourceType.DataBaseType value() default DataSourceType.DataBaseType.master; // 默认使用master库
}

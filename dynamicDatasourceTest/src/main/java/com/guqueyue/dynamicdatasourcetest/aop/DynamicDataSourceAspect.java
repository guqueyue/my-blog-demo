package com.guqueyue.dynamicdatasourcetest.aop;

import com.guqueyue.dynamicdatasourcetest.annotation.MyDataSource;
import com.guqueyue.dynamicdatasourcetest.config.DataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Author: guqueyue
 * @Description: 自定义注解拦截，使用AOP来增强对象
 * @Date: 2023/12/25
 **/
@Slf4j
@Aspect
@Component
public class DynamicDataSourceAspect {

    // 拦截自定义注解, @annotation()里面是注解的路径，需要根据你自己的替换
    @Before("@annotation(com.guqueyue.dynamicdatasourcetest.annotation.MyDataSource)")
    public void changeDataSource(JoinPoint point) {

//        // 获取增强方法
//        MethodSignature methodSignature = (MethodSignature) point.getSignature();
//        // 获取增强方法的反射对象
//        Method method = methodSignature.getMethod();
//        // 获取@MyDataSource注解
//        MyDataSource myDataSource = method.getAnnotation(MyDataSource.class);

        String dataSourceType = "master";
        // 获取方法的第一个参数
        Object[] args = point.getArgs();
        if (args != null && args.length > 0) {
            dataSourceType = args[0].toString();
        }

        // 选择数据源
        switch (dataSourceType) {
            case "slave":
                DataSourceType.setDataBaseType(DataSourceType.DataBaseType.slave);
                break;
            default: // 默认为主数据源
                DataSourceType.setDataBaseType(DataSourceType.DataBaseType.master);
                break;
        }
    }

    // 在方法执行完之后清除特定数据源的配置，使用默认数据源
    @After("@annotation(myDataSource)")
    public void restoreDataSource(JoinPoint point, MyDataSource myDataSource) {
        DataSourceType.clearDataBaseType();
    }
}

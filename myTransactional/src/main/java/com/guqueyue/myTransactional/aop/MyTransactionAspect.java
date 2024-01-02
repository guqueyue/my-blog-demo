package com.guqueyue.myTransactional.aop;

import com.guqueyue.myTransactional.util.MyTransactionalUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

/**
 * @Author: guaueyue
 * @Description: 通过拦截自定义注解实现事务
*                @annotation()里为自定义注解的路径
 * @Date: 2023/12/28
 **/
@Slf4j
@Aspect
@Component
public class MyTransactionAspect {

    @Autowired
    private MyTransactionalUtil transactionalUtil;

    /**
     * @Description 通过拦截自定义注解@MyTransactional来实现事务
     * @Param [joinPoint]
     * @return java.lang.Object
     **/
    @Around(value = "@annotation(com.guqueyue.myTransactional.annotation.MyTransactional)")
    public Object around(ProceedingJoinPoint joinPoint) {

        Object result = null;
        TransactionStatus begin = null;
        try {
            // 开启事务
            begin = transactionalUtil.begin();

            // 执行目标方法
            result = joinPoint.proceed();

            // 提交事务
            transactionalUtil.commit(begin);

        } catch (Throwable e) {
            transactionalUtil.rollback(begin);
            log.info("事务回滚...");
            e.printStackTrace();
        }

        return result;
    }
}

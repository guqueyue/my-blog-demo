package com.guqueyue.myTransactional.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @Author: guqueyue
 * @Description: 自定义事务工具类
 * @Date: 2023/12/28
 **/
@Component
public class MyTransactionalUtil {

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * @Description 开启事务
     * @Param []
     * @return org.springframework.transaction.TransactionStatus
     **/
    public TransactionStatus begin() {

        return dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
    }

    /**
     * @Description 提交事务
     * @Param [transactionStatus]
     * @return void
     **/
    public void commit(TransactionStatus transactionStatus) {

        if (transactionStatus != null) {
            dataSourceTransactionManager.commit(transactionStatus);
        }
    }

    /**
     * @Description 回滚事务
     * @Param [transactionStatus]
     * @return void
     **/
    public void rollback(TransactionStatus transactionStatus) {
        if (transactionStatus != null) {
            dataSourceTransactionManager.rollback(transactionStatus);
        }
    }
}

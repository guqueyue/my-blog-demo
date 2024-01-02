package com.guqueyue.myTransactional.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guqueyue.myTransactional.annotation.MyTransactional;
import com.guqueyue.myTransactional.dao.UserMapper;
import com.guqueyue.myTransactional.entity.User;
import com.guqueyue.myTransactional.service.IUserService;
import com.guqueyue.myTransactional.util.MyTransactionalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.sql.SQLException;

/**
 * @Author: guqueyue
 * @Description: 用户实现类
 * @Date: 2023/12/19
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private MyTransactionalUtil myTransactionalUtil;

    @Resource
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertUser(User user) throws Exception {

        int result = userMapper.insert(user);
        // 写一个异常
        FileInputStream fileInputStream = new FileInputStream("");

        return result;
    }

    @Transactional
//    @Override
    public Integer insertUser5(User user) {

        int result = 0;
        try {

            result = userMapper.insert(user);
            // 写一个异常
            FileInputStream fileInputStream = new FileInputStream("");

        }catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }


    @Transactional
//    @Override
    public Integer insertUser4(User user) {

        int result = userMapper.insert(user);
        // 写一个异常
        int i = 1/0;

        return result;
    }


    @MyTransactional
//    @Override
    public Integer insertUser3(User user) {

        int result = userMapper.insert(user);
        // 写一个异常
        int i = 1/0;

        return result;
    }

//    @Override
    public Integer insertUser2(User user) {

        int result = 0;
        TransactionStatus begin = null;
        try {
            // 开启事务
            begin = myTransactionalUtil.begin();

            result = userMapper.insert(user);
            // 写一个异常
            int i = 1/0;

            // 提交事务
            myTransactionalUtil.commit(begin);

        }catch (Exception e) {
            e.printStackTrace();

            // 回滚事务
            myTransactionalUtil.rollback(begin);

//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 手动回滚
            return 0;
        }

        return result;
    }

//    @Override
    public Integer insertUser1(User user) {

        int result = userMapper.insert(user);
        // 写一个异常
        int i = 1/0;

        return result;
    }
}
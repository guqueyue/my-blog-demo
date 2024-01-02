package com.guqueyue.myTransactional.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guqueyue.myTransactional.dao.UserMapper;
import com.guqueyue.myTransactional.entity.User;
import com.guqueyue.myTransactional.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: guqueyue
 * @Description: 用户实现类
 * @Date: 2023/12/19
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

}
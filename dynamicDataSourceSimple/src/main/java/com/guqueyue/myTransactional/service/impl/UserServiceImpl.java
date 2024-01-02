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

    @Override
    public List<User> selectUserList(String type) {

        // do something: 此处可根据实际情况进行业务选择，如根据当前登录的用户信息来选择不同的数据库
        // 此处为了方便演示，直接用前端传入的type来判断

        return "slave".equals(type) ? userMapper.selectUserListBySlave()
                                    : userMapper.selectUserList();
    }
}
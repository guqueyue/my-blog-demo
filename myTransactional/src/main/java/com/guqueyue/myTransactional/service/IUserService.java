package com.guqueyue.myTransactional.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guqueyue.myTransactional.entity.User;

import java.io.FileNotFoundException;

/**
 * @Author: guqueyue
 * @Description: 用户service接口
 * @Date: 2023/12/19
 **/
public interface IUserService extends IService<User> {
    Integer insertUser(User user) throws FileNotFoundException, Exception;
}
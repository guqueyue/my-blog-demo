package com.guqueyue.dynamicdatasourcetest.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.guqueyue.dynamicdatasourcetest.entity.User;

import java.util.List;

/**
 * @Author: guqueyue
 * @Description: 用户service接口
 * @Date: 2023/12/25
 **/
public interface IUserService extends IService<User> {


    List<User> selectUserList(String type);
}
package com.guqueyue.dynamicdatasourcetest.controller;

import com.guqueyue.dynamicdatasourcetest.entity.User;
import com.guqueyue.dynamicdatasourcetest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: guqueyue
 * @Description: 用户控制层
 * @Date: 2023/12/25
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 查询用户列表
     * @return
     */
    @RequestMapping("/list")
    public List<User> userList(String type) {
        return userService.selectUserList(type);
    }
}
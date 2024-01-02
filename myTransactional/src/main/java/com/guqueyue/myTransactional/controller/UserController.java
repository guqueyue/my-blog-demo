package com.guqueyue.myTransactional.controller;

import com.guqueyue.myTransactional.entity.User;
import com.guqueyue.myTransactional.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: guqueyue
 * @Description: 用户控制层
 * @Date: 2023/12/19
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 插入用户
     * @return
     */
    @RequestMapping("/insertUser")
    public Integer insertUser(User user) throws Exception {
        System.out.println("接收到的用户为：" + user);

        return userService.insertUser(user);
    }
}
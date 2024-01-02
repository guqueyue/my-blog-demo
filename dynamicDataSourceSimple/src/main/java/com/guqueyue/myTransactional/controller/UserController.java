package com.guqueyue.myTransactional.controller;

import com.guqueyue.myTransactional.entity.User;
import com.guqueyue.myTransactional.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     * 查询用户列表
     * @return
     */
    @RequestMapping("/list")
    public List<User> userList(String type) {
        System.out.println("接收到的数据源类型为：" + type);

        return userService.selectUserList(type);
    }
}
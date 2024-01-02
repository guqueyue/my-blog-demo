package com.guqueyue.dynamicdatasourcetest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guqueyue.dynamicdatasourcetest.annotation.MyDataSource;
import com.guqueyue.dynamicdatasourcetest.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: guqueyue
 * @Description: 映射接口UserMapper
 * @Date: 2023/12/25
 **/
public interface UserMapper extends BaseMapper<User> {

    @MyDataSource
    @Select("SELECT id,password,username FROM users")
    List<User> selectUserList(String type);
}

package com.guqueyue.myTransactional.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guqueyue.myTransactional.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: guqueyue
 * @Description: 映射接口UserMapper
 * @Date: 2023/12/19
 **/
@DS("master") // 默认为主数据源，其实可以省略
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from users")
    List<User> selectUserList();

    @DS("slave") // 切换为slave数据源
    @Select("select * from users")
    List<User> selectUserListBySlave();

}
package com.guqueyue.dynamicdatasourcetest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: guqueyue
 * @Description: 用户实体类
 * @Date: 2023/12/25
 **/
//lombok插件的注解
@Data // 若未使用lombok插件，请自行生成getter、setter以及toString方法
@AllArgsConstructor // 若未使用lombok插件，请自行生成有参构造方法
@NoArgsConstructor // 若未使用lombok插件，请自行生成无参构造方法
@Accessors(chain = true) // 开启链式编程
@TableName("users") // 因为实体类和表名不一致, 需要进行配置
public class User implements Serializable {

    // 主键自动递增
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;
    private String password;
}

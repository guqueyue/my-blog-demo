package com.guqueyue.dynamicdatasourcetest.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: guqueyue
 * @Description: 用以设置数据源
 * @Date: 2023/12/25
 **/
@Slf4j
public class DataSourceType {

    // 内部枚举类，用以选择特定的数据源
    public enum DataBaseType {
        master,
        slave
    }

    // 使用ThreadLocal保证线程安全
    private static final ThreadLocal<DataBaseType> TYPE = new ThreadLocal<>();

    // 往当前线程中设置数据源类型
    public static void setDataBaseType(DataBaseType dataBaseType) {
        if (dataBaseType == null) {
            throw new NullPointerException();
        }

        TYPE.set(dataBaseType);
        log.info("\033[31;0;42;30;1;2;3;4;41m" + "线程:[" + Thread.currentThread().getName() +"],取了[" + getDataBaseType() + "]=>这个数据源");
    }

    // 获取数据源类型
    public static DataBaseType getDataBaseType() {
        return TYPE.get() == null ? DataBaseType.master : TYPE.get();
    }

    // 清空数据源类型
    public static void clearDataBaseType() {
        TYPE.remove();
    }
}

package com.guqueyue.dynamicdatasourcetest.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author: guqueyue
 * @Description: 通过扩展determineCurrentLookupKey()方法来实现数据源的切换
 * @Date: 2023/12/25
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceType.getDataBaseType();
    }
}

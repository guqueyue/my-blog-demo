package com.guqueyue.dynamicdatasourcetest.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: guqueyue
 * @Description: 多数据源配置类
 * @Date: 2023/12/25
 **/
@Configuration
@MapperScan(basePackages = "com.guqueyue.dynamicdatasourcetest.dao", sqlSessionFactoryRef = "SqlSessionFactory") // basePackages为接口地址
public class DynamicDataSourceConfig {

    // 将这个对象放入Spring容器中
    @Bean(name = "masterDataSource")
    // 表示这个数据源为默认数据源
    @Primary
    // 读取 application.yml 中的配置参数映射成为一个对象
    // prefix表示配置文件中参数的前缀
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource getMasterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource getSlaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource, @Qualifier("slaveDataSource") DataSource slaveDataSource) {

        // 核心点: targetDataSource为 数据源和注入的Bean 之间的映射
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceType.DataBaseType.master, masterDataSource);
        targetDataSource.put(DataSourceType.DataBaseType.slave, slaveDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(masterDataSource); // 设置默认数据源
        return dataSource;
    }

    @Bean(name = "SqlSessionFactory")
    @DependsOn("globalConfig") // 明确调用顺序，在调用本方法前先调用 globalConfig() 方法
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception {

//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);

        // 设置xml文件路径
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/guqueyue/dynamicdatasourcetest/dao/*.xml"));
        // 设置别名的扫描 - 实体类的包名引用
        bean.setTypeAliasesPackage("com.guqueyue.dynamicdatasourcetest.entity");
        bean.setGlobalConfig(globalConfig());
        return bean.getObject();
    }

    /**
     * @Description 解决Oracle数据库下，用 @KeySequence注解 实现主键序列自增失效 问题解决
     * @Param []
     * @return com.baomidou.mybatisplus.core.config.GlobalConfig
     **/
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setDbConfig(new GlobalConfig.DbConfig().setKeyGenerator(new OracleKeyGenerator()));
        return globalConfig;
    }

    @Bean(name = "sqlSessionTemplate")
    @Primary // 首选
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * @Description 事务管理
     * @Param [dataSource]
     * @return org.springframework.jdbc.datasource.DataSourceTransactionManager
     **/
    @Bean(name = "dataSourceTransactionManager")
    @Primary // 首选
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dynamicDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

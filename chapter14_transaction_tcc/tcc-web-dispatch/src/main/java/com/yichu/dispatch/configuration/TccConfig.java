package com.yichu.dispatch.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.mengyun.tcctransaction.TransactionRepository;
import org.mengyun.tcctransaction.serializer.KryoTransactionSerializer;
import org.mengyun.tcctransaction.serializer.ObjectSerializer;
import org.mengyun.tcctransaction.spring.recover.DefaultRecoverConfig;
import org.mengyun.tcctransaction.spring.repository.SpringJdbcTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2019/4/22
 * Desc:
 */
@Configuration
@ImportResource(locations = "classpath:tcc-transaction.xml")
public class TccConfig {

    @Autowired
    private TccDataSourceProperties properties;

    @Bean
    public TransactionRepository transactionRepository(ObjectSerializer<?> serializer) {
        SpringJdbcTransactionRepository repository = new SpringJdbcTransactionRepository();
        repository.setDataSource(druidDataSource());
        repository.setDomain("DISPATCH");       //领域, 或者也可以称为模块名、应用名, 用于唯一标识一个资源
        repository.setTbSuffix("_DISPATCH");    //表后缀, 默认存储表名为 TCC_TRANSACTION  ==> tcc_transaction_dispatch
        repository.setSerializer(serializer);
        return repository;
    }

    /**
     * 设置恢复策略
     * @return
     */
    @Bean
    public DefaultRecoverConfig defaultRecoverConfig() {
        DefaultRecoverConfig defaultRecoverConfig=new DefaultRecoverConfig();
        defaultRecoverConfig.setCronExpression("0 */1 * * * ?");
        defaultRecoverConfig.setMaxRetryCount(120);
        defaultRecoverConfig.setMaxRetryCount(30);
        return defaultRecoverConfig;
    }

    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(properties.getUrl());
        datasource.setDriverClassName(properties.getDriverClassName());
        datasource.setUsername(properties.getUsername());
        datasource.setPassword(properties.getPassword());
        datasource.setInitialSize(10);
        datasource.setMinIdle(1);
        datasource.setMaxWait(6000);
        datasource.setMaxActive(10);
        datasource.setMinEvictableIdleTimeMillis(1800000);
        return datasource;
    }

    @Bean
    public ObjectSerializer<?> objectSerializer() {
        return new KryoTransactionSerializer();
    }

}

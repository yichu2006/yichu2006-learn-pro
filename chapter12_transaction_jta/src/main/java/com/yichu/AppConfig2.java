//package com.yichu;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//
///***
// * 普通数据源 系统配置
// *
// * @author Tony
// *
// */
//@Configuration
//public class AppConfig2 {
//
//    /** 订单系统数据库 */
//    @Bean(name = "orderDataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "mall.order-datasource")
//    public DataSource orderDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "orderJdbcTemplate")
//    @Primary
//    public JdbcTemplate orderJdbcTemplate(@Qualifier("orderDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    /** 调度系统数据库 */
//    @Bean(name = "dispatchDataSource")
//    @ConfigurationProperties(prefix = "mall.dispatch-datasource")
//    public DataSource dispatchDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "dispatchJdbcTemplate")
//    public JdbcTemplate dispatchJdbcTemplate(
//            @Qualifier("dispatchDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//}

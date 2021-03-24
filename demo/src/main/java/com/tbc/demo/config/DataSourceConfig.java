package com.tbc.demo.config;

import com.alibaba.druid.pool.DruidDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

//@Configuration
public class DataSourceConfig {

    public static NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();

    private static final String paasowrd = "544535975";
    private static final String userName = "root";
    private static final String url = "jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static final String driver = "com.mysql.jdbc.Driver";


    @Bean
    public static NamedParameterJdbcTemplate getJdbcTemplate() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setPassword(paasowrd);
        druidDataSource.setUsername(userName);
        return new NamedParameterJdbcTemplate(druidDataSource);
    }
}

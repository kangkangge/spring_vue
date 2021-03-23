package com.tbc.demo.config;

import com.alibaba.druid.pool.DruidDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

//@Configuration
public class DataSourceConfig {

    public NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();

    private final String paasowrd = "544535975";
    private final String userName = "root";
    private final String url = "jdbc:mysql://localhost/test?serverTimezone=Asia/Shanghai";
    private final String driver = "com.mysql.jdbc.Driver";


    @Bean
    public NamedParameterJdbcTemplate getJdbcTemplate() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setPassword(paasowrd);
        druidDataSource.setUsername(userName);
        return new NamedParameterJdbcTemplate(druidDataSource);
    }
}

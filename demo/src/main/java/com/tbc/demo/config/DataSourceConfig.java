package com.tbc.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {

    @Bean("cloudJdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("org.postgresql.Driver");
        druidDataSource.setUrl("jdbc:postgresql://172.21.0.18:5432/ps");
        druidDataSource.setPassword("Eln4postgres");
        druidDataSource.setUsername("postgres");
        jdbcTemplate.setDataSource(druidDataSource);
        return jdbcTemplate;
    }
}

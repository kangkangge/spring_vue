package com.tbc.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.tbc.demo.common.model.TagManager;

import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

//@Configuration
public class DataSourceConfig {

    public NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();

    private final String paasowrd = "544535975";
    private final String userName = "root";
    private final String url = "jdbc:mysql://localhost:3306/test";
    private final String driver = "com.mysql.jdbc.Driver";


    public NamedParameterJdbcTemplate getJdbcTemplate() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setPassword(paasowrd);
        druidDataSource.setUsername(userName);
        return new NamedParameterJdbcTemplate(druidDataSource);
    }

    @Test
    public void test() {
        String sql = "select * from t_tag_manager";
        List<TagManager> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TagManager.class));
        System.out.println(query);
    }

}

package com.tbc.demo.catalog.error_page;

import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    @Bean
    public ErrorPageConfig errorPageRegistrar() {
        return new ErrorPageConfig();
    }
}


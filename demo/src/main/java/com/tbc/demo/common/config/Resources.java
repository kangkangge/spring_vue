package com.tbc.demo.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author gkk
 * @date 2019/7/19 21:45
 * @return
 * @describe 资源配置类
 * @version 1.0
 */

//表示这个类是一个读取配置文件的类
@Configuration
//指定配置的一些属性,其中的prefix表示前缀
@ConfigurationProperties(prefix = "com.tbc.demo")
//指定所读取的配置文件的路径
@PropertySource(value = "classpath:application.properties")
public class Resources {


}

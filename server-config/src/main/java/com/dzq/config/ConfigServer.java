package com.dzq.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;

/**
 * @Project : SpringCloudFramework
 * @Package Name : com.dzq.config
 * @Description : TODO 配置中心
 * @Author : daizhiqing@xiaochong.com
 * @Creation Date : 2018年02月28日下午2:40
 * @ModificationHistory Who When What
 */
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigServer {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServer.class, args);
    }
}

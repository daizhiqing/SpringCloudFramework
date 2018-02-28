package com.dzq.example;

import com.dzq.base.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * @Project : SpringCloudFramework
 * @Package Name : com.dzq.example
 * @Description : TODO
 * @Author : daizhiqing@xiaochong.com
 * @Creation Date : 2018年02月28日下午3:13
 * @ModificationHistory Who When What
 */
@EnableEurekaClient
@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = Config.BASE_PACKAGE)
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
public class ExampleApp {
    private static final Logger logger = LoggerFactory.getLogger(ExampleApp.class);

    public static void main(String[] args) {
        SpringApplication.run(ExampleApp.class, args);

        logger.debug("日志输出测试 Debug");

        logger.trace("日志输出测试 Trace");

        logger.info("日志输出测试 Info");

        logger.warn("日志输出测试 Warn");

        logger.error("日志输出测试 ERROR");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}

package com.dzq.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Project : SpringCloudFramework
 * @Package Name : PACKAGE_NAME
 * @Description : TODO 服务注册中心
 * @Author : daizhiqing@xiaochong.com
 * @Creation Date : 2018年02月28日下午2:31
 * @ModificationHistory Who When What
 */

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer.class, args);
    }

}

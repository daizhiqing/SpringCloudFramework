package com.dzq.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @Project : SpringCloudFramework
 * @Package Name : com.dzq.turbine
 * @Description : TODO
 * @Author : daizhiqing@xiaochong.com
 * @Creation Date : 2018年02月28日下午5:33
 * @ModificationHistory Who When What
 */
@SpringBootApplication
@EnableTurbine
@EnableHystrix
@EnableDiscoveryClient
public class TurbineApp {

    public static void main(String[] args) {
        SpringApplication.run(TurbineApp.class, args);
    }

}

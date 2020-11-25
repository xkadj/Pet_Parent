package com.lcq.pet.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: Health_Parent
 * @description:
 * @author:
 * @create: 2020-11-25 14:55
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lcq.pet.server.dao")
@EnableDiscoveryClient
public class AuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class,args);
    }
}

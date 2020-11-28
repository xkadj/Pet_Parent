package com.lcq.pet.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan(basePackages = "com.lcq.pet.server.dao")
@EnableDiscoveryClient
public class NetWorkApplication {
    public static void main(String[] args) {
        SpringApplication.run(NetWorkApplication.class,args);
    }
}
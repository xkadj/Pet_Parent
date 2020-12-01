package com.lcq.pet.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: Health_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-11-29 11:44
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PetGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PetGatewayApplication.class,args);

    }
}

package com.lujun61;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumeSimpleQueryOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumeSimpleQueryOrderApplication.class, args);
    }

}

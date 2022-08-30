package com.lujun61;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.lujun61.orderadd.dao")
@EnableEurekaClient      // 启用eureka服务：服务提供者
public class ProviderOrderAddApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderOrderAddApplication.class, args);
    }

}

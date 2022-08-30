package com.lujun61;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient    // 作为消费者发现服务
@EnableHystrix            // 启用熔断器
public class ConsumerSaveOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerSaveOrderApplication.class, args);
    }

}

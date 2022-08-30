package com.lujun61.saveorder.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RibbonConfig {

    /**
     * @description 注册Ribbon客户端
     * @author Jun Lu
     * @date 2022-08-29 19:36:40
     */
    @LoadBalanced   //启⽤Ribbon（负载均衡）
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

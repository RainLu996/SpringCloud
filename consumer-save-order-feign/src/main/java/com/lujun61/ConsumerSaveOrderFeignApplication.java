package com.lujun61;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient     // 作为消费者发现服务
@EnableFeignClients        // 声明启用Feign客户端
@EnableHystrix            // 启用熔断器
public class ConsumerSaveOrderFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerSaveOrderFeignApplication.class, args);
    }


    // feign-dashboard 配置注意2
    // SpringCloud之SpringBoot2.0版本要想使用hystrix的dashboard，需要配置一个servlet，配置访问路径
    @Bean
    public ServletRegistrationBean getServletRegistrationBean(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setName("HystrixMetricsStreamServlet");
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        return registrationBean;
    }
}

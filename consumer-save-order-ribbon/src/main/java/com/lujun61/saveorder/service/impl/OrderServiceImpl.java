package com.lujun61.saveorder.service.impl;

import com.lujun61.saveorder.entity.Order;
import com.lujun61.saveorder.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Jun Lu
 * @description 简单测试Ribbon，更多操作详见Feign
 * @date 2022-08-28 10:10:53
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private RestTemplate restTemplate;


    // Ribbon中熔断器使用配置
    @HystrixCommand(fallbackMethod = "fallbackSaveOrder",     // 指定降级响应方法
            commandProperties = {
                /* 1、服务降级策略配置 */
                // 3秒钟超时时间：3秒钟之内服务未成功响应，则打回降级响应
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),

                /** 对服务熔断的理解：
                 *          熔断器默认为【关闭】状态。
                 *          但是当⽤户请求服务A，服务A调⽤服务B，如果服务B的故障率达到特定的阈值，
                 *      熔断器就会在⼀个时间周期（默认5s，可⾃定义）内被【打开】；在这个时间周期内如果⽤户请求服务A，
                 *      服务A将不再调⽤服务B，⽽是直接发送“降级响应”。
                 *          当时间周期结束时，熔断器会进入【半开】状态。
                 *          这时，如果A给B发送了一个请求，且B成功响应，则熔断器进入{闭合}状态；
                 *          若响应失败，则进入{开启}状态，再进入一个时间周期的熔断。
                 */
                /* 2、服务熔断策略配置 */
                //启⽤服务熔断器。熔断器状态：闭合、打开、半开
                @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                // 熔断周期
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
                // 熔断周期内的请求次数标准
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                // 熔断周期内的请求次数中，失败的请求所占的百分比
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
    })
    @Override
    public void addOrder(Order order) {
        /**
         * 1、根据服务名到eureka查找可用的服务列表。
         * 2、得到服务列表后，负载均衡采用轮询策略访问服务。（因为配置RestTemplate时加上了LoadBalance注解）
         */
        /*                                          调用order-add服务          参数     返回值类型    */
        //String res = restTemplate.postForObject("http://order-add/order/postOpt", order, String.class);
        String res = restTemplate.getForObject("http://order-add/order/getOpt?name=" + order.getName(), String.class);

        System.out.println("res=" + res);
    }

    private void fallbackSaveOrder(Order order) {
        System.out.println("降级响应");
    }
}

package com.lujun61.saveorder.service.impl;

import com.lujun61.saveorder.entity.Order;
import com.lujun61.saveorder.service.OrderService;
import com.lujun61.saveorder.service.feign.SaveOrderFeignClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    /**
     * 1、根据服务名到eureka查找可用的服务列表。
     * 2、得到服务列表后，负载均衡采用轮询策略访问服务。
     */
    @Resource
    // 当我们创建Feign客户端的降级类并交给Spring管理后，在Spring容器中就会出现两个OrderAddClient对象
    // 但是Spring只会在降级服务 生效时 注入 自定义的降级类
    private SaveOrderFeignClient saveOrderFeignClient;

    @Override
    public void addOrder(Order order) {
        String res = saveOrderFeignClient.postOpt(order);

        System.out.println("postRes：" + res);
    }

    @Override
    public void delOrder(String id) {
        String res = saveOrderFeignClient.deleteOpt(id);

        System.out.println("delRes：" + res);
    }

    @Override
    public void updateOrder(Order order) {
        String res = saveOrderFeignClient.putOpt(order);

        System.out.println("updateRes：" + res);
    }

    @Override
    public void getOrder(String name) {
        String res = saveOrderFeignClient.getOpt(name);

        System.out.println("getRes：" + res);
    }
}

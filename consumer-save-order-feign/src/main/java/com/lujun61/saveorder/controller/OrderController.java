package com.lujun61.saveorder.controller;

import com.lujun61.saveorder.entity.Order;
import com.lujun61.saveorder.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description 为方便传值，这里接收用户请求的方式都是Get
 * @author Jun Lu
 * @date 2022-08-28 09:40:38
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    // http://localhost:9004/order/search?name=lujun
    @GetMapping("/search")
    public void get(String name) {
        orderService.getOrder(name);
    }

    // http://localhost:9004/order/add?name=lujun&id=1
    @GetMapping("/add")
    public void post(Order order) {
        orderService.addOrder(order);
    }

    // http://localhost:9004/order/update?name=lujun&id=1
    @GetMapping("/update")
    public void update(Order order) {
        orderService.updateOrder(order);
    }

    // http://localhost:9004/order/del?id=1
    @GetMapping("/del")
    public void del(String id) {
        orderService.delOrder(id);
    }
}

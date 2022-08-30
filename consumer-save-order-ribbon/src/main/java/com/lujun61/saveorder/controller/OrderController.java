package com.lujun61.saveorder.controller;

import com.lujun61.saveorder.entity.Order;
import com.lujun61.saveorder.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    // http://localhost:9003/order/save?name=lujun
    @GetMapping("/save")
    public void add(Order order) {

        System.out.println("--------------------成功调用save-add");

        orderService.addOrder(order);
    }

}

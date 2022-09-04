package com.lujun61.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryOrderController {

    // http://localhost:9002/query?name=lujun
    @GetMapping("/query")
    public String queryOrder(String name) {
        return "我是order-query服务~~~~~" + name;
    }

}

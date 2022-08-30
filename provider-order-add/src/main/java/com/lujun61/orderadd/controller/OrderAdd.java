package com.lujun61.orderadd.controller;

import com.lujun61.orderadd.entity.Order;
import org.springframework.web.bind.annotation.*;

/**
 * @description 模拟微服务通信中的各种请求
 * @author Jun Lu
 * @date 2022-08-28 09:28:55
 */
@RequestMapping("/order")
@RestController
public class OrderAdd {

    @PostMapping("/postOpt")   // 增
    public String postOpt(@RequestBody Order order) {

        System.out.println("--------------------成功调用postOpt");

        return "----------------" + order.getId() + order.getName();

    }

    @PutMapping("/putOpt")   // 改
    public String putOpt(@RequestBody Order order) {

        System.out.println("--------------------成功调用postOpt");

        return "----------------" + order.getId() + order.getName();

    }

    @GetMapping("/getOpt")  // 查
    public String getOpt(@RequestParam("name") String name) {

        System.out.println("--------------------成功调用getOpt");

        return "----------------" + name;

    }

    @DeleteMapping("/deleteOpt")  // 删
    public String deleteOpt(@RequestParam("id") String id) {

        System.out.println("--------------------成功调用deleteOpt");

        return "----------------" + id;

    }


}

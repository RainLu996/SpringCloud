package com.lujun61.saveorder.service.feign;

import com.lujun61.saveorder.entity.Order;
import com.lujun61.saveorder.service.feign.fallback.SaveOrderFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * @description  FeignClient客户端。
 *                  相比于Ribbon注入式，更方便了。
 *                  毕竟，Feign底层使用的是：Ribbon + Hystrix
 * @author Jun Lu
 * @date 2022-08-27 22:11:18
 */
//            目标访问的服务名：application-name                      指定降级处理类
@FeignClient(value = "order-add",                               fallback = SaveOrderFeignClientFallback.class)
public interface SaveOrderFeignClient {


    /** Feign传值规则：
     * 1.用POST请求调⽤服务，Feign客户端的⽅法参数默认为body传值（body只能有⼀个值）
     * 2.如果有多个参数，则需要通过@RequestParam声明参数为 请求⾏ 进行传递
     */
    @PostMapping("order/postOpt")   // 增
    String postOpt(@RequestBody Order order);

    @PutMapping("order/putOpt")   // 改
    String putOpt(@RequestBody Order order);

    @GetMapping("order/getOpt")  // 查
    String getOpt(@RequestParam("name") String name);

    @DeleteMapping("order/deleteOpt")  // 删
    String deleteOpt(@RequestParam("id") String id);
}

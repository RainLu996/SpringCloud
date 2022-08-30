package com.lujun61.saveorder.service.feign.fallback;

import com.lujun61.saveorder.entity.Order;
import com.lujun61.saveorder.service.feign.SaveOrderFeignClient;
import org.springframework.stereotype.Component;


/**
 * @description FeignClient的服务降级类：
 *                  1.必须实现Feign客户端接⼝
 *                  2.必须交给Spring容器管理
 * @author Jun Lu
 * @date 2022-08-29 20:52:28
 */
@Component
public class SaveOrderFeignClientFallback implements SaveOrderFeignClient {
    @Override
    public String postOpt(Order order) {
        return "fail";
    }

    @Override
    public String putOpt(Order order) {
        return "fail";
    }

    @Override
    public String getOpt(String name) {
        return "fail";
    }

    @Override
    public String deleteOpt(String id) {
        return "fail";
    }
}

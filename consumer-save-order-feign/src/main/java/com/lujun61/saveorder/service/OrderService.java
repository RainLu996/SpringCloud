package com.lujun61.saveorder.service;

import com.lujun61.saveorder.entity.Order;

public interface OrderService {

    void addOrder(Order order);

    void delOrder(String id);

    void updateOrder(Order order);

    void getOrder(String name);


}

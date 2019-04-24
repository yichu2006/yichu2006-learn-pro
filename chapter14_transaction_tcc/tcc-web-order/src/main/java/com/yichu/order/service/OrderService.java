package com.yichu.order.service;

import com.yichu.order.entity.Order;

/**
 *
 */
public interface OrderService {

    /**
     * 创建订单
     */
    void createOrder(Order order) throws Exception;
}

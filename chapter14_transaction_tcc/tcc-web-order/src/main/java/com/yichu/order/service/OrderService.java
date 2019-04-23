package com.yichu.order.service;

/**
 *
 */
public interface OrderService {

    /**
     * 创建订单
     */
    void createOrder(String userId, String orderContent) throws Exception;
}

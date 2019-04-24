package com.yichu.order.service;

import com.alibaba.fastjson.JSONObject;
import com.yichu.api.service.DispatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderDatabaseService orderDatabaseService;

    @Autowired()  //这里按类型装配  @Autowired()@Qualifier("testClient") 也可以通过名称来装配
    DispatchService dispatchService;

    /**
     * 创建订单
     * 
     * @throws Exception
     */
    public void createOrder(String userId, String orderContent) throws Exception {
        // 订单号生成
        String orderId = UUID.randomUUID().toString();
        JSONObject orderInfo = new JSONObject();
        orderInfo.put("orderId", orderId);
        orderInfo.put("userId", userId);
        orderInfo.put("orderContent", orderContent);

        // 1. 数据库操作   保存订单
        orderDatabaseService.saveOrder(orderInfo);  //这里面还是有事务的

        // 2. 调用 分单
        dispatchService.dispatch(orderId);

        System.out.println("订单创建成功");

    }
}

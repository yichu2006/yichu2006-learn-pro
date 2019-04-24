package com.yichu.order.service;

import com.yichu.api.service.DispatchService;
import com.yichu.order.entity.Order;
import org.mengyun.tcctransaction.api.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    @Compensable(confirmMethod = "confirmOrder", cancelMethod = "cancelOrder", asyncConfirm = true)
    public void createOrder(Order order) throws Exception {

        // 1. 数据库操作   保存订单
        orderDatabaseService.saveOrder(order);  //这里面还是有事务的
        System.out.println("订单创建成功");

        // 2. 调用 分单
        dispatchService.dispatch(null, order.getOrderId());
    }

    public void confirmOrder(Order order) {
        //手动抛异常
        int ii = 1/0;
        System.out.println("订单系统 confirm");
    }

    public void cancelOrder(Order order) {

        System.out.println("订单系统 cancel");
        orderDatabaseService.deleteOrder(order);
    }


}

package com.yichu.test;

import com.yichu.order.OrderApplication;
import com.yichu.order.entity.Order;
import com.yichu.order.service.OrderService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class OrderApplicationTests {
    @Before
    public void start() {
        System.out.println("开始测试##############");
    }

    @After
    public void finish() {
        System.out.println("结束##############");
    }

    @Autowired
    OrderService orderService;

    @Test
    public void orderCreate() throws Exception {

        // 订单号生成
        String orderId = UUID.randomUUID().toString();
        Order order = new Order();
        order.setOrderId(orderId);
        order.setUserId("tony");
        order.setOrderContent("买了根黄瓜");
        order.setStatus("DRAFT");  // 初始订单状态

        orderService.createOrder(order);
    }

}

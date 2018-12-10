package com.yichu.十一个行为型模式.strategy.strategy2;

import org.springframework.stereotype.Component;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/6
 * Desc: 提升1
 */
@Component
public class promotion1 implements PromotionCalculation {
    @Override
    public Order calculate(Order order) {
        order.setOrderPrice((float) (order.getOrderPrice() * 0.9));
        return order;
    }
}

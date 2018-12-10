package com.yichu.十一个行为型模式.strategy.strategy2;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/6
 * Desc: 计算订单接口
 */
public interface PromotionCalculation {

    Order calculate(Order order);
}

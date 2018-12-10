package com.yichu.十一个行为型模式.strategy.strategy2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/6
 * Desc:
 */
@Component
public class OrderService {

    @Autowired
    private PromotionCalculationFactory promotionCalculationFactory;

    public Order prepareOrder(Order order, String promotion) {
/*        switch (promotion){
            case "promotion-1":
                //促销1的算法
                //...
                break;
            case "promotion-2":
                //促销1的算法
                //...
                break;
            case "promotion-3":
                //促销1的算法
                //...
                break;
            //....
        }
        return order;*/

        //策略模式改造
        promotionCalculationFactory.getPromotionCalculation(promotion).calculate(order);
        return order;
    }

    public Order prepareOrder2(Order order, String... promotion) {
        //策略模式改造
        for (String p : promotion) {
            order = promotionCalculationFactory.getPromotionCalculation(p).calculate(order);
        }
        return order;
    }
}
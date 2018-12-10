package com.yichu.十一个行为型模式.strategy.strategy2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/6
 * Desc: 订单打折 控制层
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //只支持1种打折方案
    @RequestMapping(value = "prepare", method = RequestMethod.POST)
    public Order prepareOrder(@RequestBody Order order, @RequestParam("promotion") String promotion) {
        return orderService.prepareOrder(order,promotion);
    }

    //支持多种组合打折方案
    @RequestMapping(value = "prepare2", method = RequestMethod.POST)
    public Order prepareOrder2(@RequestBody Order order, @RequestParam("promotion") String... promotion) {
        return orderService.prepareOrder2(order,promotion);
    }




}

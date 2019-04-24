package com.yichu.order.web;

import com.yichu.order.entity.Order;
import com.yichu.order.service.OrderService;
import com.yichu.order.service.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/order")
public class OrderController {
	private final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	OrderService orderService;

	/**
	 * 创建订单  用户编号  订单内容(购买了什么东西)
	 * 可以通过 单元测试类 来测试
	 */
	@RequestMapping("/create")
	public Object createOrder(String userId, String orderContent) {
		// 调用service方法获取
		try {
			// 订单号生成
			String orderId = UUID.randomUUID().toString();
			Order order = new Order();
			order.setOrderId(orderId);
			order.setUserId(userId);
			order.setOrderContent(orderContent);
			order.setStatus("DRAFT");  // 初始订单状态

			orderService.createOrder(order);
		} catch (Exception e) {
			logger.error("出错啦", e);
			return ">>>>>>>>>>>>>>>>>failed<<<<<<<<<<<<<<<<<<<";
		}
		return ">>>>>>>>>>>>>>>>>successfully<<<<<<<<<<<<<<<<<<<";
	}

}

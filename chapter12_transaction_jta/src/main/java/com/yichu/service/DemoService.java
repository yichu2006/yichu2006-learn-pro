package com.yichu.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

@Service
public class DemoService {

	@Autowired
	DispatchService dispatchService;

	@Autowired
	OrderDatabaseService orderDatabaseService;

	@Transactional(rollbackFor = Exception.class)
	public void inserRecord() throws Exception {
		// 订单信息生成
		String orderId = UUID.randomUUID().toString();
		JSONObject orderInfo = new JSONObject();
		orderInfo.put("orderId", orderId);
		orderInfo.put("userId", "testUser");
		orderInfo.put("orderContent", "买了根黄瓜");

		// 1. 保存订单记录
		orderDatabaseService.saveOrder(orderInfo);
		// 2. 往数据库插入一条记录 调度系统数据库事务2
		dispatchService.dispatch(orderId);
	}
}

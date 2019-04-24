package com.yichu.order.service;

import com.alibaba.fastjson.JSONObject;
import com.yichu.order.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 数据库操作相关的service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderDatabaseService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 1.保存订单记录
     */
    public void saveOrder(Order order) throws Exception {
        String sql = "insert into table_order (order_id, user_id, order_content, create_time) values (?, ?, ?,now())";
        // 1. 添加订单记录
        int count = jdbcTemplate.update(sql, order.getOrderId(), order.getUserId(), order.getOrderContent());

        if (count != 1) {
            throw new Exception("订单创建失败，原因[数据库操作失败]");
        }
    }

    /**
     * 2.删除订单
     */
    public void deleteOrder(Order order) {
        String sql = "DELETE FROM table_order WHERE order_id = ?";
        // 1. 添加订单记录
        jdbcTemplate.update(sql, order.getOrderId());
    }
}

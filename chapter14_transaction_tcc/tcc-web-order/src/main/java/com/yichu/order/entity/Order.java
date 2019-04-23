package com.yichu.order.entity;

import java.util.Date;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2019/4/22
 * Desc:
 */
public class Order {

    private String orderId;

    private String userId;

    private String orderContent;

    private Date createTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

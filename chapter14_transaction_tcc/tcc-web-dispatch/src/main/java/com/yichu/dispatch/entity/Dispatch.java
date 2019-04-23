package com.yichu.dispatch.entity;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2019/4/23
 * Desc:
 */
public class Dispatch {

    //调度流水号
    private String dispatchSeq;

    //订单编号
    private String orderId;

    //调度状态
    private String dispatch_status;

    //调度内容(送餐员，路线)
    private String dispatchContent;

    public String getDispatchSeq() {
        return dispatchSeq;
    }

    public void setDispatchSeq(String dispatchSeq) {
        this.dispatchSeq = dispatchSeq;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDispatch_status() {
        return dispatch_status;
    }

    public void setDispatch_status(String dispatch_status) {
        this.dispatch_status = dispatch_status;
    }

    public String getDispatchContent() {
        return dispatchContent;
    }

    public void setDispatchContent(String dispatchContent) {
        this.dispatchContent = dispatchContent;
    }
}

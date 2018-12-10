package com.yichu.七个结构型模式.adapter;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/7
 * Desc: 被适配者（USB接口）
 */
public class Adaptee {
    public void handleReq() {
        System.out.println("可以接受到客户端的请求");
    }
}

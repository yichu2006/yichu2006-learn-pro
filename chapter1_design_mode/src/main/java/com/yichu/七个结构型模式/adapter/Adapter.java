package com.yichu.七个结构型模式.adapter;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/7
 * Desc: 类适配器(USB转接头)
 */
public class Adapter extends Adaptee implements Target {
    @Override
    public void handleReq() {
        super.handleReq();
    }
}

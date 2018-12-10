package com.yichu.七个结构型模式.adapter;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/7
 * Desc: 对象适配器（USB转接口）
 */
public class Adapter2 implements Target {

    private Adaptee adaptee;

    public Adapter2(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void handleReq() {
        adaptee.handleReq();
    }
}

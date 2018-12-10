package com.yichu.七个结构型模式.proxy.jdk;

public class ZhangSan implements People {

    @Override
    public void eat(String params) {
        System.out.println("正在吃" + params);
    }
}

package com.yichu.七个结构型模式.decorator;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/6
 * Desc: 具体组件
 */
public class ConcreteComponent implements Component {
    @Override
    public String methodA() {
        return "concrete-object";
    }

    @Override
    public int methodB() {
        return 100;
    }
}

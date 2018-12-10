package com.yichu.七个结构型模式.decorator;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/6
 * Desc: 装饰角色
 */
public class Decorator implements Component {

    protected Component component;

    public Decorator(Component component) {
        super();
        this.component = component;
    }

    @Override
    public String methodA() {
        return this.component.methodA();
    }

    @Override
    public int methodB() {
        return this.component.methodB();
    }
}

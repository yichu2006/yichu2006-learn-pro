package com.yichu.七个结构型模式.decorator;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/6
 * Desc: 具体装饰角色
 */
public class DecoratorA extends Decorator {

    public DecoratorA(Component component) {
        super(component);
    }

    public String methodA(){
        return this.component.methodA() + "A";
    }

    public int methodB(){
        return this.component.methodB() + 10;
    }
}

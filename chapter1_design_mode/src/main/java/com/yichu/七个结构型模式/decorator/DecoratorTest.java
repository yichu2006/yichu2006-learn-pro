package com.yichu.七个结构型模式.decorator;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/6
 * Desc: 装饰者模式
 */
public class DecoratorTest {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        component = new DecoratorA(component);
        System.out.println(component.methodA());
        System.out.println(component.methodB());
    }
}

package com.yichu.七个结构型模式.proxy.jdk;

import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {

        People people = (People)Proxy.newProxyInstance(Test.class.getClassLoader(),
                new Class<?>[]{People.class},
                new ProxyInvocationHandler(new ZhangSan()));

        people.eat("猪手");
    }
}

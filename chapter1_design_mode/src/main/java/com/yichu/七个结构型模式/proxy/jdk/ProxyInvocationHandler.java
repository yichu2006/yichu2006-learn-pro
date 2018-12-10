package com.yichu.七个结构型模式.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvocationHandler implements InvocationHandler {

    private People people;

    public ProxyInvocationHandler(People people) {
        this.people = people;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //前置增强
        before();

        method.invoke(people, args);

        //后置增强
        after();
        return null;

    }

    private void before() {
        System.out.println("我在吃猪脚之前必须要洗手！！！");
    }

    private void after() {
        System.out.println("吃完猪脚之后我必须要洗手！！！");
    }
}

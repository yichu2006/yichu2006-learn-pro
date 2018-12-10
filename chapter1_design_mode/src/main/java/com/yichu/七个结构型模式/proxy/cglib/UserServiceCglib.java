package com.yichu.七个结构型模式.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/5
 * Desc:
 */
public class UserServiceCglib implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 设置回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    /**
     * 实现MethodInterceptor接口中重写的方法
     *
     * 回调方法
     */
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        //前置增强
        System.out.println("事务开始。。。");

        Object result = proxy.invokeSuper(object, args);
//        Object result = method.invoke(target, args);

        //后置增强
        System.out.println("事务结束。。。");
        return result;
    }

}
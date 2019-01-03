package com.yichu.atomicint;

import java.util.concurrent.atomic.AtomicInteger;

/**

 * 创建日期：2017/11/30
 * 创建时间: 15:37
 */
public class AtomicIntTest {
    static AtomicInteger ai = new AtomicInteger(1);
    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.incrementAndGet());
        System.out.println(ai.get());
    }
}

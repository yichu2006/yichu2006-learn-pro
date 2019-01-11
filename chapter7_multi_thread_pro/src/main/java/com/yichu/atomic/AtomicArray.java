package com.yichu.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**

 * 创建日期：2017/11/30
 * 创建时间: 20:49
 */
public class AtomicArray {
    static int[] value = new int[]{1,2};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0,3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }

}

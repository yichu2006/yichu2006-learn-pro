package com.yichu.interrupt;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2019/1/9
 * Desc:
 */
public class Test {

    public static void main(String[] args) {

        Test test = new Test();

        try {
            test.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

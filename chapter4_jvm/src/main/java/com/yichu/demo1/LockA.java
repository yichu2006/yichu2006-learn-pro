package com.yichu.demo1;

import java.util.Date;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/12
 * Desc:
 */
public class LockA implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(new Date().toString() + "LockA 开始执行");
            while (true) {
                synchronized (LoginController.obj1) {
                    System.out.println(new Date().toString() + "LockA 锁住 obj1");
                    Thread.sleep(3000);  //此处等待是给B能锁住的机会
                    synchronized (LoginController.obj2) {
                        System.out.println(new Date().toString() + "LockB 锁住 obj2");
                        Thread.sleep(60 * 1000);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

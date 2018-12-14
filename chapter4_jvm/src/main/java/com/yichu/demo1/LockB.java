package com.yichu.demo1;

import java.util.Date;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/12
 * Desc:
 */
public class LockB implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(new Date().toString() + "LockB 开始执行");
            while (true) {
                synchronized (LoginController.obj2) {
                    System.out.println(new Date().toString() + "LockB 锁住 obj2");
                    Thread.sleep(3000);  //此处等待是给A能锁住的机会
                    synchronized (LoginController.obj1) {
                        System.out.println(new Date().toString() + "LockB 锁住 obj1");
                        Thread.sleep(60 * 1000);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.yichu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建日期：2017/11/30
 * 创建时间: 21:36
 */
public class ConditionTemplete {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void waitc() throws InterruptedException {
        lock.lock();
        try{
            condition.await();
        }finally{
            lock.unlock();
        }
    }

    public void waitnotify() throws InterruptedException {
        lock.lock();
        try{
            condition.signal();   //因为 condition 就已经知道 哪个线程要被唤醒了。
            //condition.signalAll();尽量少使用
        }finally{
            lock.unlock();
        }
    }


}

package com.yichu;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/11/27
 * Desc:
 */
public class SellTicket implements Runnable {

    private int tickets = 100;

    //加锁的三种方式： 单机锁jvm、 分布式锁（redis、Redisson）
    //jdk 锁
//    private Lock lock = new ReentrantLock();

    //redis 分布式锁
    private Lock lock = new RedisLock();

    //获取分布式锁
//    RLock lock = getRedissonLock();

    private RLock getRedissonLock() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        Redisson redisson = (Redisson)Redisson.create(config);
        RLock lock = redisson.getLock("foobar");  //1. 获取锁对象实例
        return lock;
    }


    public void run() {
        while (tickets > 0) {
            lock.lock();
            try {
                if (tickets > 0) {
                    try {
                        Thread.sleep(100);

                        //这段逻辑可以 证明 不可重入
                        lock.lock();
                        try{
                            System.out.println(Thread.currentThread().getName() + "正在出售第" + tickets + "张票");
                        }finally {
                            lock.unlock();
                        }


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "正在出售第" + tickets-- + "张票");
                }
            } finally {
                lock.unlock();
            }


        }
    }
}

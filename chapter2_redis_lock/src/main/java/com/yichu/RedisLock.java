package com.yichu;

import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/11/28
 * Desc:
 */
public class RedisLock implements Lock {

    //阻塞式加锁
    public void lock() {
        if (!tryLock()) {
            try {
                Thread.sleep(new Random().nextInt(10) + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock();
        }
    }

    public void lockInterruptibly() throws InterruptedException {

    }

    //非阻塞式加锁
    public boolean tryLock() {
        Jedis jedis = new Jedis("localhost");
        String result = jedis.set("lock", Thread.currentThread().getName(), "NX", "EX", 10000);
        if ("OK".equals(result)) {
            return true;
        }
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {
        Jedis jedis = new Jedis("localhost");
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList("lock"), Collections.singletonList(Thread.currentThread().getName()));
    }

    public Condition newCondition() {
        return null;
    }
}

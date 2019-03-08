package com.yichu;

import redis.clients.jedis.Jedis;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/11/28
 * Desc:  缺点 不可重入
 */
public class RedisLock implements Lock {

    private static ThreadLocal<String> tokenMap = new ThreadLocal<>();

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

        String token = tokenMap.get();
        if(token == null || token.equals("")) {
            token = UUID.randomUUID().toString();
            tokenMap.set(token);
        }

        Jedis jedis = new Jedis("localhost");
        String result = jedis.set("lock", token, "NX", "EX", 10000);

        String lockValue = jedis.get("lock");
        if ("OK".equals(result) || token.equals(lockValue)) {  //实现redis锁 可重入
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
        Object result = jedis.eval(script, Collections.singletonList("lock"), Collections.singletonList(tokenMap.get()));
    }

    public Condition newCondition() {
        return null;
    }
}

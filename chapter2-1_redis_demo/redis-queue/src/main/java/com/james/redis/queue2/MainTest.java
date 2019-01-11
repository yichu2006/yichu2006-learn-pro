package com.james.redis.queue2;

import java.util.Random;

public class MainTest {  
    public static void main(String[] args) throws Exception {  
    	new Random().nextInt(13);
        // 启动一个生产者线程，模拟任务的产生  
        new Thread(new RedisProducer()).start();  
          
        Thread.sleep(15000);  
          
        //启动一个线程者线程，模拟任务的处理  
        new Thread(new RedisConsumer()).start();  
          
        //主线程休眠  
        Thread.sleep(Long.MAX_VALUE);  
    }  
}  

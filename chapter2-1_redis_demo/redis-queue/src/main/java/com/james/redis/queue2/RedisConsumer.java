package com.james.redis.queue2;


import java.util.Random;
import com.james.redis.utils.JedisUtils;

/*
 * 消费者
 */
public class RedisConsumer implements Runnable {   
	JedisUtils jedis = new JedisUtils("192.168.1.111",6379,"12345678");
    public void run() {  
        Random random = new Random();  
          
        while(true){  
            //从任务队列"test-queue"中获取一个任务，并将该任务放入暂存队列"tmp-queue"  
            String taskid = jedis.rpoplpush("test-queue", "tmp-queue");  
            // 处理任务----纯属业务逻辑，模拟一下：睡觉  
            try {  
                Thread.sleep(1000);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
              
            //随机产生一个大于等于0，小于13的整形数。  
            //模拟成功和失败的偶然现象  
            if(random.nextInt(13) < 2){// 模拟失败的情况,概率为2/13  
                //将本次处理失败的任务从暂存队列"tmp-queue"中，弹回任务队列"test-queue"  
                jedis.rpoplpush("tmp-queue", "test-queue");  
                System.out.println(taskid + "处理失败，被弹回消息队列");  
              
            } else {// 模拟成功的情况  
                  
                // 将本次任务从暂存队列"tmp-queue"中清除  
                jedis.rpop("tmp-queue");  
                System.out.println(taskid+"消息处理成功，被清除");  
                  
            }     
        }  
                          
    }  
      
  
      
}  
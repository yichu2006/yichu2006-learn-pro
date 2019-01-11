package com.james.redis.queue2;


import java.util.Random;
import java.util.UUID;
import com.james.redis.utils.JedisUtils;

/*
 * 生产者
 */

public class RedisProducer implements Runnable{  
	JedisUtils jedis = new JedisUtils("192.168.1.111",6379,"12345678");
      
    public void run() {  
        Random random = new Random();  
        while(true){  
            try{  
                Thread.sleep(random.nextInt(600) + 600);  
                // 模拟生成一个任务  
                UUID orderId = UUID.randomUUID();  
                //将任务插入任务队列：task-queue  
                jedis.lpush("test-queue", orderId.toString());  
                System.out.println("队列最左端进入了新的消息数据： " + orderId);  
  
            }catch(Exception e){  
                e.printStackTrace();  
            }  
        }  
          
    }  
  
}  
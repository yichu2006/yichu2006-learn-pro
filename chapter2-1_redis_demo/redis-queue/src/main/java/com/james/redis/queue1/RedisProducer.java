package com.james.redis.queue1;

import com.james.redis.utils.JedisUtils;

public class RedisProducer {  
  
    /**  
     * jedis操作List  
     */    
    public static void main(String[] args){  
  
        JedisUtils jedis = new JedisUtils("192.168.1.111", 6379,"12345678");   
        for(int i = 0;i<10;i++) {  
            jedis.lpush("informList","orderIdadb_" + i);    
        }  
    }  
  
} 
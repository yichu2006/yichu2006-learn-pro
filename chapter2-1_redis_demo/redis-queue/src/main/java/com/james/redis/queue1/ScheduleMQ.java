package com.james.redis.queue1;

import java.util.List;

import com.james.redis.utils.JedisUtils;

class ScheduleMQ extends Thread {  
    JedisUtils jedis = new JedisUtils("192.168.1.111", 6379,"12345678");   
  
    @Override  
    public void run() {  
        while(true) {  
            //阻塞式brpop，List中无数据时阻塞  
            //参数0表示一直阻塞下去，直到List出现数据  
            List<String> list = jedis.brpop(0, "informList");  
            for(String s : list) {  
            	//处理业务逻辑
                System.out.println(s);  
            }  
  
        }  
    }  
}  
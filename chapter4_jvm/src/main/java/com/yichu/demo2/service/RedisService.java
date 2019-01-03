package com.yichu.demo2.service;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/14
 * Desc:
 */
@Service
public class RedisService {

    private final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Timed  //表示该方法加入 metrics 监控： 收集这个方法的调用次数，平均响应时间，最大最小响应时间。。。
    public String queryFromRedis(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = new Jedis();
            //从redis中获取值
            value = jedis.get(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return value;
    }
}

package com.yichu.demo2.service;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/14
 * Desc:
 */
@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private HttpService httpService;

    @Autowired
    private RedisService redisService;

    public Object getUserInfo(String userId) throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();

        //先从redis中获取用户信息
        String value = redisService.queryFromRedis(userId);
        JSONObject userInfo = JSONObject.parseObject(value);

        //再从接口获取用户积分信息
        String intergral = httpService.queryForRest(userId);
        JSONObject intergralInfo = JSONObject.parseObject(intergral);

        //合并为一个json对象
        userInfo.putAll(intergralInfo);

        logger.error("getUserInfo执行时间" + (System.currentTimeMillis() - currentTimeMillis));
        return userInfo;
    }
}

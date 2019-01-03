package com.yichu.demo2.service;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/14
 * Desc:
 */
@Service
public class HttpService {

    private final Logger logger = LoggerFactory.getLogger(HttpService.class);

    @Timed  //功能损耗低，计算全在内存，结果输出是异步
    public String queryForRest(String userId) throws InterruptedException {
        //调用http接口
//        return restTemplate.getForObject("http://www.yichu.com/userInfo/get?userId=" + userId, String.class);

        int nextint = new Random().nextInt(10);
        if(nextint <= 1) {
            Thread.sleep(new Random().nextInt(5) * 10000L);
        }
        return "{\"name\":\"" + userId + "\",\"intergral\":998}";
    }
}

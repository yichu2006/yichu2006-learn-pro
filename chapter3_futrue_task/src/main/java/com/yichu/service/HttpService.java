package com.yichu.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpService {

	private final Logger logger = Logger.getLogger(HttpService.class);

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 获取用户信息
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 */
	public String queryUserInfo(String userId) {
		long start = System.currentTimeMillis();
		// 调用获取用户基础信息的http接口
//		String result = restTemplate.getForObject("http://www.yichu.com/userinfo-api/get?userId=" + userId, String.class);
		String result = "{\"用户信息\":\"yichu\"}";
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.error("获取用户信息接口调用耗时:" + (System.currentTimeMillis() - start));

		return result;
	}

	/**
	 * 获取用户积分
	 * 
	 * @param userId
	 * @return
	 */
	public String queryUserIntegral(String userId) {
		long start = System.currentTimeMillis();
		// 调用获取用户积分信息的接口
//		String result = restTemplate.getForObject("http://www.yichu.com/integral-api/get?userId=" + userId, String.class);
		String result = "{\"用户积分\":\"100\"}";
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.error("获取积分信息接口调用耗时:" + (System.currentTimeMillis() - start));

		return result;
	}
}

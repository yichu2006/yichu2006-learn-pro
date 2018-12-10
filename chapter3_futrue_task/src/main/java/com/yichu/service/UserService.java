package com.yichu.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 串行调用http接口
 */
@Component
public class UserService {

	private final Logger logger = Logger.getLogger(UserService.class);

	@Autowired
	HttpService httpService;

	public Object getUserInfo(String userId) {
		// 调用
		long currentTimeMillis = System.currentTimeMillis();
		// 先从接口中获取用户信息
		String value = httpService.queryUserInfo(userId);
		JSONObject userInfo = JSONObject.parseObject(value);

		// 再从接口获取用户积分信息
		String intergral = httpService.queryUserIntegral(userId);
		JSONObject intergralInfo = JSONObject.parseObject(intergral);

		// 合并为一个json对象
		JSONObject result = new JSONObject();
		result.putAll(userInfo);
		result.putAll(intergralInfo);

		logger.error("getUserInfo执行时间" + (System.currentTimeMillis() - currentTimeMillis));
		return result;
	}

}

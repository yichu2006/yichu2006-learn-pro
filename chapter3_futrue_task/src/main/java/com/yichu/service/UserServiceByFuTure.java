package com.yichu.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 通过创建Thread，多线程并行的方式
 * 
 * @author zhuangWenBin
 *
 */
@Component
public class UserServiceByFuTure {

	private final Logger logger = Logger.getLogger(UserServiceByFuTure.class);

	@Autowired
	HttpService httpService;

	public Object getUserInfo(final String userId) throws Exception {

		// 调用
		long currentTimeMillis = System.currentTimeMillis();

		// 创建Callable - 查询基础信息
		Callable<JSONObject> queryUserInfoCallable = new Callable<JSONObject>() {
			public JSONObject call() throws Exception {
				// 接口中获取用户信息
				String value = httpService.queryUserInfo(userId);
				return JSONObject.parseObject(value);
			}
		};
		//Callable 是在 Runnable的run中被调用的
		FutureTask<JSONObject> queryUserInfoFutureTask = new FutureTask<>(queryUserInfoCallable);

		// 创建Callable - 查询积分
		Callable<JSONObject> queryUserIntegralCallable = new Callable<JSONObject>() {
			public JSONObject call() throws Exception {
				// 接口获取用户积分信息
				String intergral = httpService.queryUserIntegral(userId);
				return JSONObject.parseObject(intergral);
			}
		};
		FutureTask<JSONObject> queryUserIntegralFutureTask = new FutureTask<>(queryUserIntegralCallable);

		// 启动线程
		// ps. 生产环境中，一定要使用线程池。
		new Thread(queryUserInfoFutureTask).start();
		new Thread(queryUserIntegralFutureTask).start();

		// 问题:什么时候返回结果？
		// 合并为一个json对象
		//get方法回去返回值的时候，是阻塞的，也就是同步的
		JSONObject result = new JSONObject();
		result.putAll(queryUserInfoFutureTask.get());
		result.putAll(queryUserIntegralFutureTask.get());

		logger.error("getUserInfo方法执行时间" + (System.currentTimeMillis() - currentTimeMillis));
		return result;
	}

}

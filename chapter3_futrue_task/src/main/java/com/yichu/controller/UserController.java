package com.yichu.controller;


import com.yichu.service.UserService;
import com.yichu.service.UserServiceByFuTure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserServiceByFuTure userServiceByFuTure;

	// 获取用户信息
	@RequestMapping(path = "/getUserInfo", produces = "application/json; charset=UTF-8")
	public Object getUserInfo(String userId) throws Exception {
		// 调用service方法获取
		return userService.getUserInfo(userId);
	}

	// 获取用户信息
	@RequestMapping(path = "/getUserInfoByFuture", produces = "application/json; charset=UTF-8")
	public Object getUserInfoByThread(String userId) throws Exception {
		// 多线程并行的方式
		return userServiceByFuTure.getUserInfo(userId);
	}
}

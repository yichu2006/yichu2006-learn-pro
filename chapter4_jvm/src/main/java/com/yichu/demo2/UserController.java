package com.yichu.demo2;

import com.yichu.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/14
 * Desc:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    //获取用户信息
    @RequestMapping("getUserInfo")
    public Object getUserInfo(String userId) throws Exception {
        return userService.getUserInfo(userId);
    }
}

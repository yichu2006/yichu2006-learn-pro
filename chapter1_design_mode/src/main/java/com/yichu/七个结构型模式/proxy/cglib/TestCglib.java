package com.yichu.七个结构型模式.proxy.cglib;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/5
 * Desc:
 */
public class TestCglib {
    public static void main(String[] args) {
        UserServiceCglib cglib = new UserServiceCglib();
        UserServiceImpl bookFacedImpl = (UserServiceImpl) cglib.getInstance(new UserServiceImpl());
        bookFacedImpl.addUser();
    }
}

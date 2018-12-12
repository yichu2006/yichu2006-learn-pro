package com.yichu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/12
 * Desc:
 */
@Controller
public class LoginController {

    public static String obj1 = "obj1";
    public static String obj2 = "obj2";

    @RequestMapping("login")
    @ResponseBody
    public String login(String type) {
        if ("busy".equals(type)) {
            //死循环
            createBusyThread();
        } else if ("lock".equals(type)) {
            Object obj = new Object();
            //锁等待
            createLockWaitThread(obj);
        } else if ("dead".equals(type)) {
            //死锁
            deadLockThread();
        }
        return "login";
    }

    //线程死循环
    public void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                }
            }
        }, "testBusyThread");
        thread.start();
    }

    //线程锁等待
    public void createLockWaitThread(final Object lock) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockWaitThread");
        thread.start();
    }

    //线程死锁
    public void deadLockThread() {
        LockA lockA = new LockA();
        new Thread(lockA).start();

        LockB lockB = new LockB();
        new Thread(lockB).start();
    }

}

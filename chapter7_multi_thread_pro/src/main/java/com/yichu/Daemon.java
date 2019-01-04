package com.yichu;


import com.yichu.threadstate.SleepUtils;

/**
 * 创建日期：2017/11/26
 * 创建时间: 12:00
 * 守护线程:  try finally 中的代码不一定执行
 */
public class Daemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner());
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                SleepUtils.second(100);
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}

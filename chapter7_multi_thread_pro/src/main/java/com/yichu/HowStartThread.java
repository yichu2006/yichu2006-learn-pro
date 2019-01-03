package com.yichu;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 创建日期：2017/11/26
 * 创建时间: 20:26
 * 如何创建一个线程
 */
public class HowStartThread {

    private static class TestThread extends Thread {
        @Override
        public void run() {
            System.out.println("TestThread is Running");
        }
    }

    private static class TestRunable implements Runnable {
        @Override
        public void run() {
            System.out.println("TestRunable is Running");
        }
    }

    private static class TestCallable implements Callable {
        @Override
        public Object call() throws Exception {
//            Thread.sleep(1000);
            TimeUnit.SECONDS.sleep(1);  //TimeUnit.MILLISECONDS.sleep
            return "ok";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*Thread t1 = new TestThread();
        Thread t2 = new Thread(new TestRunable());
        t1.start();
        t2.start();*/

        Callable<String> callable = new TestCallable();
        FutureTask<String> task = new FutureTask<>(callable);
        new Thread(task).start();
        String result = task.get();
        System.out.println("hello : " + result);
    }
}


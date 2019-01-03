package com.yichu.interrupt;

/**

 * 创建日期：2017/11/26
 * 创建时间: 20:42
 * 安全的中断线程
 */
public class SafeInterrupt implements Runnable {

    private volatile boolean on = true;
    private long i = 0;

    @Override
    public void run() {
        while (on && !Thread.currentThread().isInterrupted()) {
            i++;
        }
        System.out.println("TestRunable is runing :" + i);
    }

    public void cancel() {
        on = false;
        Thread.currentThread().interrupt();  //线程中断标志位 置为 true
    }

    public static void main(String[] args) throws InterruptedException {
        SafeInterrupt safeInterrupt = new SafeInterrupt();
        Thread t = new Thread(safeInterrupt);
        t.start();
        Thread.sleep(100);
        safeInterrupt.cancel();
    }
}

package com.yichu;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderServiceImpl implements Runnable {

    private static OrderCodeGenerator ong = new OrderCodeGenerator();

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private static final int NUM = 100;
    // 按照线程数初始化倒计数器
    private static CountDownLatch cdl = new CountDownLatch(NUM);

    	private  Lock lock = new ReentrantLock();
//	private Lock lock = new ZookeeperLock();         
//    private Lock lock = new ZookeeperImproveLock();

    // 创建订单接口   这里也可以不用lock ，方法上加 synchronized
    public void createOrder() {
        String orderCode = null;
        lock.lock();
        try {
            // 获取订单编号
            orderCode = ong.getOrderCode();
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            lock.unlock();
        }

        // ……业务代码，此处省略100行代码

        logger.info(Thread.currentThread().getName()
                + " =======================>" + orderCode);
    }

    @Override
    public void run() {
        try {
            // 等待其他线程初始化
            cdl.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 创建订单
        createOrder();
    }

    public static void main(String[] args) {
        for (int i = 1; i <= NUM; i++) {
            // 按照线程数迭代实例化线程
            new Thread(new OrderServiceImpl()).start();
            // 创建一个线程，倒计数器减1
            cdl.countDown();
        }
    }

}

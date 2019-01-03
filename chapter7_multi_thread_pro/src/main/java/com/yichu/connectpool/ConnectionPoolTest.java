package com.yichu.connectpool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ����ѧԺ-Mark��ʦ
 * �������ڣ�2017/11/15
 * ����ʱ��: 17:06
 */
public class ConnectionPoolTest {
    static ConnectionPool pool  = new ConnectionPool(10);
    // ��֤����ConnectionRunner�ܹ�ͬʱ��ʼ
    static CountDownLatch start = new CountDownLatch(1);
    // main�߳̽���ȴ�����ConnectionRunner��������ܼ���ִ��
    static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        // �߳������������߳��������й۲�
        int threadCount = 50;
        end = new CountDownLatch(threadCount);
        int count = 20;//ÿ���߳�ѭ��ȡ20��
        AtomicInteger got = new AtomicInteger();//��ȡ�����ݿ����ӵĴ���
        AtomicInteger notGot = new AtomicInteger();//û�л�ȡ�����ݿ����ӵĴ���
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnetionRunner(count, got, notGot),
                    "ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection:  " + got);
        System.out.println("not got connection " + notGot);
    }

    static class ConnetionRunner implements Runnable {
        int           count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnetionRunner(int count, AtomicInteger got,
                               AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        public void run() {
            try {
                start.await();
            } catch (Exception ex) {

            }
            while (count > 0) {
                try {
                    // ���̳߳��л�ȡ���ӣ����1000ms���޷���ȡ�������᷵��null
                    // �ֱ�ͳ�����ӻ�ȡ������got��δ��ȡ��������notGot
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception ex) {
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}

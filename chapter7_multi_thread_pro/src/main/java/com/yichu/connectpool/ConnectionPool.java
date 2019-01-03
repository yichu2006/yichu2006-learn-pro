package com.yichu.connectpool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * ����ѧԺ-Mark��ʦ
 * �������ڣ�2017/11/15
 * ����ʱ��: 17:06
 * ���ݿ����ӳ�
 * �����ӳ��л�ȡ��ʹ�ú��ͷ����ӵĹ��̣����ͻ��˻�ȡ���ӵĹ��̱��趨Ϊ�ȴ���ʱ��ģʽ��
 * Ҳ������1000����������޷���ȡ���������ӣ����᷵�ظ��ͻ���һ��null��
 * �趨���ӳصĴ�СΪ10����Ȼ��ͨ�����ڿͻ��˵��߳�����ģ���޷���ȡ���ӵĳ�����
 * ���ӳصĶ��塣��ͨ�����캯����ʼ�����ӵ�������ޣ�ͨ��һ��˫�������ά�����ӣ�
 * ���÷���Ҫ�ȵ���fetchConnection(long)������ָ���ڶ��ٺ����ڳ�ʱ��ȡ���ӣ�������ʹ����ɺ�
 * ��Ҫ����releaseConnection(Connection)���������ӷŻ��̳߳�
 */
public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<Connection>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.getConnectiong());
            }
        }
    }

    /*�����ӷŻ��̳߳�*/
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                // ��Ӻ���Ҫ����֪ͨ�����������������ܹ���֪�����ӳ����Ѿ��黹��һ������
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /*ָ���ڶ��ٺ����ڳ�ʱ��ȡ���ӣ���ָ��ʱ�����޷���ȡ�����ӣ����᷵��null
    */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            // ��ȫ��ʱ
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}

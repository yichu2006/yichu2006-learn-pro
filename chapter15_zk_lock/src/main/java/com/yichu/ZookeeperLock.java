package com.yichu;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperLock implements Lock {
	
	private static final String ZK_IP_PROT = "localhost:2181";
	private static final String LOCK_NODE = "/LOCK";
	
	private ZkClient client = new ZkClient(ZK_IP_PROT);
	
	private static final Logger logger = LoggerFactory.getLogger(ZookeeperLock.class);
	
	private CountDownLatch cdl=null;
	

	@Override
	//阻塞的方式去获取锁
	public void lock() {
		if(tryLock()){
			logger.info("=============get lock success==============");
		}else{
			waitForLock(); //阻塞
			lock();
		}

	}

	@Override
	//通过新建节点的方式去尝试加锁  非阻塞   这里创建的是 持久节点
	public boolean tryLock() {
		try {
			//充分利用zk的特性： 同一节点下 持久节点和临时节点  节点名称是唯一的
			client.createPersistent(LOCK_NODE);
			return true;
		} catch (ZkNodeExistsException e) {
			return false;
		}
	}

	@Override
	public void unlock() {
		//删除持久节点
		client.delete(LOCK_NODE);
	}

	//利用zk的 监听器特性
	private void waitForLock() {
		//1.创建一个监听  匿名类
		IZkDataListener listener = new IZkDataListener() {
			
			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				//3.当其他的线程释放锁，抛出事件，让其他线程重新竞争锁
				logger.info("=============catch data delete event==============");
				if(cdl != null) {
					cdl.countDown();
				}
			}
			
			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				// TODO Auto-generated method stub
				
			}
		};

		client.subscribeDataChanges(LOCK_NODE, listener); //节点加监听

		//2.如果节点还存在，让当前线程阻塞
		if(client.exists(LOCK_NODE)) {
			cdl = new CountDownLatch(1);
			try {
				cdl.await();  //这里等， 只要其他线程释放锁的时候触发handleDataDeleted事件
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		client.unsubscribeDataChanges(LOCK_NODE, listener);  //节点释放监听
		
	}
	
	//----------------------------------------

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub

	}
	
	
	@Override
	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch cdl = new CountDownLatch(1);
		ZkClient client = new ZkClient(ZK_IP_PROT);
		client.subscribeDataChanges(LOCK_NODE, new IZkDataListener() {
			
			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println("===============aaa===========");
				cdl.countDown();
				
			}
			
			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				
			}
		});
		
		cdl.await();
	}


}

package com.demo;

public class SynchronizedDemo {
	private int a=1;
	private boolean flag = false;
	private int result = 0;
	
	public /*synchronized*/ void write(){//1
		flag = true;// 1.1
		a = 2;// 1.2
		/*
		 * 这个方法中的指令会发生指令重排
		 * 1.1 》 2.1 》 2.2 》 1.2  result:2
		 * 1.1 》 2.1 》1.2 》 2.2	result:3
		 * 1.2 》 1.1 》 2.1 》 2.2	result:3
		 * 1.2 》 1.1 》 2.1 》 2.2	result:3
		 * 2.1> 2.3> 1.1 >1.2	result:0
		 * ...
		 */
	}
	public /*synchronized*/ void read(){//2
		if(flag){// 2.1
			result = a + 1;// 2.2
		}
		System.out.println("result: " + result);//2.3
	}
	
	private class MyThread extends Thread{
		private boolean readOrWriteFlag = false;// true 写操作，false 读操作
		public MyThread(boolean readOrWriteFlag){
			this.readOrWriteFlag = readOrWriteFlag;
		}
		@Override
		public void run() {
			if(readOrWriteFlag){
				write();
			}else{
				read();
			}
		}
	}
	
	public static void main(String[] args) {
		SynchronizedDemo demo = new SynchronizedDemo();
//		true 写操作，false 读操作
		demo.new MyThread(true).start();//1
		demo.new MyThread(false).start();//2
	}
}

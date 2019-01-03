package com.demo;

public class ThreadDemo extends Thread{

	private volatile boolean flag = false;
	
	public void changeFlag(){
		flag = true;
		System.out.println("changeFlag: " + Thread.currentThread().getName());
	}
	
	@Override
	public void run() {
		System.out.println("run0: " + Thread.currentThread().getName());
		int i=0;
		while(!flag){
			i ++;
		}
		System.out.println(i);
		
		System.out.println("run1: " + Thread.currentThread().getName());
	}
	
	public static void main(String[] args) throws InterruptedException {
		ThreadDemo t = new ThreadDemo();
		t.start();
		Thread.sleep(1000);
		t.changeFlag();
	}
}

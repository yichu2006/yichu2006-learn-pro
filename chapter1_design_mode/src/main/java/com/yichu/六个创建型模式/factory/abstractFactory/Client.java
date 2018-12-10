package com.yichu.六个创建型模式.factory.abstractFactory;

public class Client {

	public static void main(String[] args) {
		CarFactory factory = new LuxuryCarFactory();
		Engine e = factory.createEngine();
		e.run();
		e.start();
		
		
	}
}

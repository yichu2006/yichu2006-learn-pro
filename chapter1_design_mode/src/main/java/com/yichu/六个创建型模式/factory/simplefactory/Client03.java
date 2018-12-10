package com.yichu.六个创建型模式.factory.simplefactory;

/**
 * 简单工厂情况下
 *
 */
public class Client03 {   //调用者
	
	public static void main(String[] args) {

		Car c1 = CarFactory3.createCar("Audi");
		Car c2 = CarFactory3.createCar("Byd");
		
		c1.run();
		c2.run();
		
	}
}

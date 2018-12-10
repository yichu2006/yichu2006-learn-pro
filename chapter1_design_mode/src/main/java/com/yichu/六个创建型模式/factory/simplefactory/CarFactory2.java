package com.yichu.六个创建型模式.factory.simplefactory;

/**
 * 简单工厂类
 */
public class CarFactory2 {
	
	public static Car createAudi(){
		return new Audi();
	}
	public static Car createByd(){
		return new Byd();
	}
	
}

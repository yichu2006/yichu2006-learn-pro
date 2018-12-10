package com.yichu.六个创建型模式.factory.simplefactory;

public class CarFactory {
	
	public static  Car createCar(String type){
		if("奥迪".equals(type)){
			return new Audi();
		}else if("比亚迪".equals(type)){
			return new Byd();
		}else{
			return null;
		}
	}
	
}

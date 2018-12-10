package com.yichu.六个创建型模式.factory.simplefactory;

/**
 * 简单工厂类
 */
public class CarFactory3 {
	
	public static Car createCar(String carName) {
		try {
			//这也可以利用spring容器，当carName == beanName的时候，就可以从spring容器中去获取对象
			Class car = Class.forName("com.yichu.六个创建型模式.factory.simplefactory." + carName);
			return (Car) car.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

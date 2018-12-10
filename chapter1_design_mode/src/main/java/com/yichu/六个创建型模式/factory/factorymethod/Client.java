package com.yichu.六个创建型模式.factory.factorymethod;


public class Client {
	public static void main(String[] args) {
		Car c1 = new AudiFactory().createCar();
		Car c2 = new BydFactory().createCar();
		
		c1.run();
		c2.run();

		//动态传参，构建工厂
		Client client = new Client();
		Car test = client.test("com.yichu.六个创建型模式.factory.factorymethod.BenzFactory").createCar();
		test.run();

	}


	public CarFactory test(String factory) {
		CarFactory carFactory = null;
		try {
			carFactory = (CarFactory)Class.forName(factory).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carFactory;
	}
}

package com.yichu.十一个行为型模式.memento;

public class Client {
	public static void main(String[] args) {
		CareTaker taker = new CareTaker();
		
		Emp emp = new Emp("zwb", 18, 900);
		System.out.println("第一次打印对象："+emp.getEname()+"---"+emp.getAge()+"---"+emp.getSalary());
		
		taker.setMemento(emp.memento());   //备忘一次
		
		emp.setAge(38);
		emp.setEname("詹姆斯");
		emp.setSalary(9000);
		System.out.println("第二次打印对象："+emp.getEname()+"---"+emp.getAge()+"---"+emp.getSalary());
		
		emp.recovery(taker.getMemento()); //恢复到备忘录对象保存的状态
		
		System.out.println("第三次打印对象："+emp.getEname()+"---"+emp.getAge()+"---"+emp.getSalary());
		
	}
}

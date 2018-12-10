package com.yichu.七个结构型模式.proxy.jdk2;

public class TeacherCang implements Girl {

	public boolean dating(float height) {
		if (height >= 1.7F) {
			System.out.println("身高可以，可以约！");
			return true;
		}
		System.out.println("身高不可以，不可约！");
		return false;
	}
}

package com.yichu.七个结构型模式.proxy.jdk2;

public class PlayGame {

	public static void main(String[] args) {
		TuHao th = new TuHao(1.7F);

		Girl tc = new TeacherCang();

		Girl tony1 = (Girl) TonyCompany.proxy(tc);

		//土豪约会明星，通过明星的经纪人
		th.dating(tony1);

		System.out.println("---------------------------------------");
	}

}

package com.yichu.七个结构型模式.proxy.staticProxy;

public class PlayGame {

	public static void main(String[] args) {
		TuHao th = new TuHao(1.7F);

		Girl tc = new TeacherCang();

		TonyProxy tonyProxy = new TonyProxy();
		tonyProxy.setGirl(tc);

		//土豪约会明星，通过明星的经纪人
		th.dating(tonyProxy);

		System.out.println("---------------------------------------");
	}

}

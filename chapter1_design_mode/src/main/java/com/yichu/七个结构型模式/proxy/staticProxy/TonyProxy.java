package com.yichu.七个结构型模式.proxy.staticProxy;

public class TonyProxy implements Girl {

	//目标对象 即 代理的对象
	private Girl girl;

	public Girl getGirl() {
		return girl;
	}

	public void setGirl(Girl girl) {
		this.girl = girl;
	}

	public boolean dating(float height) {
		// 前置增强
		doSomethingBefore();
		boolean res = this.girl.dating(height);
		// 后置增强
		doSomethingAfter();
		return res;
	}

	private void doSomethingBefore() {
		System.out.println("老板，这个我试过了，很不错，推荐给你！");
	}
	
	private void doSomethingAfter() {
		System.out.println("老板，你觉得怎样，欢迎下次再约！");
	}

}

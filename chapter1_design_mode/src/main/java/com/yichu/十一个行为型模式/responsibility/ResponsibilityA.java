package com.yichu.十一个行为型模式.responsibility;

public class ResponsibilityA implements Responsibility {

	@Override
	public void process(Request request, ResponsibilityChain chain) {
		System.out.println("Responsibility-A done something...");
		chain.process(request);
	}

}

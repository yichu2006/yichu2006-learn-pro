package com.yichu.十一个行为型模式.responsibility;

public class ResponsibilityB implements Responsibility {

	@Override
	public void process(Request request, ResponsibilityChain chain) {
		System.out.println("Responsibility-B done something...");
		chain.process(request);
	}

}

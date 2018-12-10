package com.yichu.十一个行为型模式.responsibility;

public class PlayGame {
	public static void main(String[] args) {
		ResponsibilityChain chain = new ResponsibilityChain();
		chain.register(new ResponsibilityA());
		chain.register(new ResponsibilityB());

		chain.process(new Request() {
		});
	}
}

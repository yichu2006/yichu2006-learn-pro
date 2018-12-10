package com.yichu.十一个行为型模式.responsibility;

public interface Responsibility {

	void process(Request request, ResponsibilityChain chain);
}

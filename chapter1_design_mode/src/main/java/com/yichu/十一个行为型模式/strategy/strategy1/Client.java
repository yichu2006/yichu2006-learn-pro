package com.yichu.十一个行为型模式.strategy.strategy1;

public class Client {
	public static void main(String[] args) {
		
		Strategy s1 = new OldCustomerManyStrategy();  //这里还是会变
		Context ctx = new Context(s1);
		
		ctx.pringPrice(998);
		
	}
}

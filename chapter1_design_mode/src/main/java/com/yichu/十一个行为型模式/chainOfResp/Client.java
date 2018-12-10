package com.yichu.十一个行为型模式.chainOfResp;

public class Client {
	public static void main(String[] args) {
		Leader a = new Director("张三");              //主任
		Leader b = new Manager("李四");               //经理
		Leader b2 = new ViceGeneralManager("李小四"); //副总经理
		Leader c = new GeneralManager("王五");        //总经理
		//组织责任链对象的关系
		a.setNextLeader(b);
		b.setNextLeader(b2);
		b2.setNextLeader(c);
		
		//开始请假操作
		LeaveRequest req1 = new LeaveRequest("TOM", 15, "回英国老家探亲！");
		a.handleRequest(req1);
		
	}
}

package com.yichu.十一个行为型模式.command;

//调用者/发起者
public class Invoke {

	//也可以通过容器List<Command>容纳很多命令对象，进行批处理。数据库底层的事务管理就是类似的结构！
	private Command command;

	public Invoke(Command command) {
		super();
		this.command = command;
	} 
	
	//业务方法 ，用于调用命令类的方法
	public void call(){
		command.execute();
	}
	
	
}

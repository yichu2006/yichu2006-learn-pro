package com.yichu.十一个行为型模式.state;

/**
 * 已预订状态
 * @author Administrator
 *
 */
public class BookedState implements State {

	@Override
	public void handle() {
		System.out.println("房间已预订！别人不能定！");
	}

}

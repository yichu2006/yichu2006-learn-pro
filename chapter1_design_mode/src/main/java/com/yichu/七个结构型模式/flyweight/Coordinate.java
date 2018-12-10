package com.yichu.七个结构型模式.flyweight;

/**
 *  外部状态UnSharedConcreteFlyWeight
 *  外部状态是随环境改变而改变的、不可以共享的状态  如棋子的位置坐标
 */
public class Coordinate {
	private int x,y;

	public Coordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}

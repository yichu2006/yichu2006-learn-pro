package com.yichu.七个结构型模式.proxy.jdk2;

//土豪
public class TuHao {

	private float height;

	public TuHao(float height) {
		super();
		this.height = height;
	}

	public float getLength() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void dating(Girl g) {
		g.dating(height);
	}

}

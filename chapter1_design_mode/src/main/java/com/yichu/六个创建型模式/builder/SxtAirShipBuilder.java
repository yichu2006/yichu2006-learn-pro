package com.yichu.六个创建型模式.builder;


public class SxtAirShipBuilder implements AirShipBuilder {
	//StringBuilder, 以后学习XML解析中，JDOM库中的类：DomBuilder,SaxBuilder
	@Override
	public Engine builderEngine() {
		System.out.println("构建X牌发动机！");
		return new Engine("X牌发动机！");
	}

	@Override
	public EscapeTower builderEscapeTower() {
		
		System.out.println("构建逃逸塔");
		return new EscapeTower("X牌逃逸塔");
	}

	@Override
	public OrbitalModule builderOrbitalModule() {
		System.out.println("构建轨道舱");
		return new OrbitalModule("X牌轨道舱");
	}	
	
}

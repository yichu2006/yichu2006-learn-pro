package com.yichu.七个结构型模式.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂类
 * 棋子的大小、颜色  相同的很多，如只有黑白棋子，所以可以放人享元池
 */
public class ChessFlyWeightFactory {
	//享元池
	private static Map<String, ChessFlyWeight> map = new HashMap<>();

	public static ChessFlyWeight getChess(String color){

		if(map.get(color)!=null){
			return map.get(color);
		}else{
			ChessFlyWeight cfw = new ConcreteChess(color);
			map.put(color, cfw);
			return cfw;
		}
		
	}
	
	
}

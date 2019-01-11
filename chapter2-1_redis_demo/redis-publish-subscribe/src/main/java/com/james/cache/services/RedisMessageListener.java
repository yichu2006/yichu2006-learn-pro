package com.james.cache.services;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.UnsupportedEncodingException;

public class RedisMessageListener implements MessageListener {

	//当发布消息到channel ， onMessage就能触发接收到
	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {
			System.out.println("====channel:====" + new String(message.getChannel()) + "\n====message:===="
					+ new String(message.getBody(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
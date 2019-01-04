package com.yichu.server.handler;


import com.yichu.server.processor.MessageProcessor;
import com.yichu.server.protocol.MessageObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SFPHandler extends SimpleChannelInboundHandler<MessageObject> {

	private MessageProcessor processor = new MessageProcessor();
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, MessageObject msg) throws Exception {
		processor.messageHandler(ctx.channel(), msg);
	}

}

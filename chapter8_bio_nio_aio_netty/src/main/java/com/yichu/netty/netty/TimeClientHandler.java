package com.yichu.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter {

	@Override
	//向服务器发送指令
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 5; i++) {
			byte[] req = "QUERY TIME ORDER".getBytes();
			ByteBuf firstMessage = Unpooled.buffer(req.length);
			firstMessage.writeBytes(req);
			ctx.writeAndFlush(firstMessage);
		}
	}

	@Override
	//接收服务器的响应
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		//buf.readableBytes():获取缓冲区中可读的字节数；
		//根据可读字节数创建数组
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, "UTF-8");
		System.out.println("Now is : "+body);
	}

	@Override
	//异常处理
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		//释放资源
		ctx.close();
	}

}

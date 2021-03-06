package com.yichu.bioNioAio.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimeServer implements Runnable {

	private Selector selector;
	private ServerSocketChannel serverChannel;
	private volatile boolean stop;

	public MultiplexerTimeServer(int port) {
		try {
			//打开ServerSocketChannel
			serverChannel = ServerSocketChannel.open();
			//设置为非阻塞模式
			serverChannel.configureBlocking(false);
			//绑定监听的端口地址
			serverChannel.socket().bind(new InetSocketAddress(port), 1024);
			//创建Selector线程
			selector = Selector.open();
			//将ServerSocketChannel注册到Selector，交给Selector监听
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("The time server is start in port:"+port);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void stop(){
		this.stop = true;
	}
	@Override
	public void run() {
		while(!stop){
			try {
				//通过Selector循环准备就绪的Key
				selector.select();
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				SelectionKey selectionKey = null;
				while(iterator.hasNext()){
					selectionKey = iterator.next();
					iterator.remove();
					try {
						handleInput(selectionKey);
					} catch (Exception e) {
						if(selectionKey!=null){
							selectionKey.cancel();
							if(selectionKey.channel()!=null){
								selectionKey.channel().close();
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(selector !=null){
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey selectionKey) throws IOException {
		if(selectionKey.isValid()){
			if (selectionKey.isAcceptable()) {
				ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
				//多路复用器监听到新的客户端连接，处理连接请求，完成TCP三次握手。
				SocketChannel client = server.accept();
				//设置为非阻塞模式
				client.configureBlocking(false);
				// 将新连接注册到多路复用器上，监听其读操作，读取客户端发送的消息。
				client.register(selector, SelectionKey.OP_READ);
			}
			if(selectionKey.isReadable()){
				SocketChannel client = (SocketChannel) selectionKey.channel();
				ByteBuffer receivebuffer = ByteBuffer.allocate(1024);
				//读取客户端请求消息到缓冲区
				int count = client.read(receivebuffer);   //非阻塞
				if (count > 0) {
					receivebuffer.flip();
					byte[] bytes = new byte[receivebuffer.remaining()]; //remaining()方法
					//从缓冲区读取消息
					receivebuffer.get(bytes);
					String body = new String(bytes, "UTF-8");
					System.out.println("The time server(Thread:"+Thread.currentThread()+") receive order : "+body);
					//将currentTime响应给客户端（客户端Channel）
					String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
					doWrite(client, currentTime);
				}else if(count < 0){
					selectionKey.channel();
					client.close();
				}else{

				}
			}
		}
	}

	private void doWrite(SocketChannel client, String currentTime) throws IOException {
		if(currentTime != null && currentTime.trim().length()>0){
			ByteBuffer sendbuffer = ByteBuffer.allocate(1024);
			sendbuffer.put(currentTime.getBytes());
			sendbuffer.flip();
			//将客户端响应消息写入到客户端Channel中。
			client.write(sendbuffer);
			System.out.println("服务器端向客户端发送数据--：" + currentTime);
		}else{
			System.out.println("没有数据");
		}
	}

}

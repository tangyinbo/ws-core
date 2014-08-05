package cn.ws.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class MySocketChannelClient {
	public static void main(String[] args) throws Exception {
		runClient();
	}
	public static void runClient() throws Exception{
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 5520));
		  //创建静态的缓冲区
		  ByteBuffer buffer = ByteBuffer.allocate(255);

		  //读取数据,到buffer中
		  socketChannel.read(buffer);
		  //将position重新置为0
		  buffer.clear();
		  //输出缓冲区的数据
		  for (int i = 0; i < buffer.array().length; i++) {
		   System.out.println((char)buffer.array()[i]);
		  }
	}
}

package cn.ws.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.rmi.server.SocketSecurityException;
import java.util.Iterator;
import java.util.Set;

import javax.print.attribute.standard.Severity;

public class MySocketChannelServer {
	public static void main(String[] args) throws Exception {
		runServer();
	}

	public static void runServer() throws Exception {
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ServerSocket sc = ssc.socket();
		sc.bind(new InetSocketAddress(5520));
		Selector selector = Selector.open();
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		int readChannel = 0;
		while (true) {
			System.out.println("-----");
			readChannel = selector.select();
			if (readChannel == 0) {
				System.out.println("readChannel :  _>\t" + readChannel);
				continue;
			}
			Set<SelectionKey> set = selector.selectedKeys();
			Iterator<SelectionKey> iterator = set.iterator();
			while(iterator.hasNext()){
				SelectionKey key = iterator.next();
				iterator.remove();
				if(key.isAcceptable()){
					//服务端
					SocketChannel client=ssc.accept();
					System.out.println("  -----the client is connect"+client);
					client.configureBlocking(false);
					client.register(selector, SelectionKey.OP_WRITE);
					ByteBuffer bb = ByteBuffer.wrap("hello client".getBytes());
					//bb.flip();
					client.write(bb);
					key.attach(client);
				}else if(key.isWritable()){
					SocketChannel socketChannel =(SocketChannel) key.channel();
					System.out.println("---------------");
				}
				key.channel().close();
			}
		}
	}
}

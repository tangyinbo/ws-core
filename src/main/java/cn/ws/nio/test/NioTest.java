package cn.ws.nio.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class NioTest {
	public static void main(String[] args) throws Exception {
		channelTransTo();
	}

	private static void channelTransTo() throws Exception {
		RandomAccessFile fromFile = new RandomAccessFile("G:/ws/aa.txt","r");
		FileChannel fromChannel= fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile("G:/ws/dd.txt", "rw");
		FileChannel toChannel = toFile.getChannel();
		
		fromChannel.transferTo( 0, fromChannel.size(),toChannel);
	}
	

	private static void channelTransForm() throws Exception {
		RandomAccessFile fromFile = new RandomAccessFile("G:/ws/aa.txt","r");
		FileChannel fromChannel= fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile("G:/ws/cc.txt", "rw");
		FileChannel toChannel = toFile.getChannel();
		
		toChannel.transferFrom(fromChannel, 0, fromChannel.size());
	}


	private static void test4() throws Exception {
		File f = new File("G:/ws/bb.txt");
		FileOutputStream fos = new FileOutputStream(f);
		RandomAccessFile raf = new RandomAccessFile("G:/ws/aa.txt","rw");
		FileChannel fc = raf.getChannel();
		ByteBuffer bb = ByteBuffer.allocate(500);
		fc.read(bb);
		bb.flip();
		FileChannel fc2=fos.getChannel();
		fc2.write(bb);
		
		fc2.close();
	}

	private static void test3() throws Exception {
		RandomAccessFile raf = new RandomAccessFile("G:/ws/aa.txt","rw");
		FileChannel fc = raf.getChannel();
		ByteBuffer bb1 = ByteBuffer.allocate(100);
		ByteBuffer bb2 = ByteBuffer.allocate(100);
		ByteBuffer[] bbs = new ByteBuffer[]{bb1,bb2};
		fc.read(bbs);
		//new FileOutputStream("").getChannel().
		bb1.flip();
		bb2.flip();
		System.out.println(bb1);
		System.out.println(bb2);
	}

	public static void test2(){
		String s = "hellotangyinbo";
		CharBuffer bb = CharBuffer.allocate(10000);
		//bb.flip();
		bb.put(s);
		bb.put("我");
		bb.flip();
		System.out.println(bb.get());
		bb.mark();
		//bb.clear();
		bb.put(s);
		bb.reset();
		System.out.println(bb.toString());
		System.out.println("capacity: -> "+bb.capacity());
		System.out.println("position: -> "+bb.position());
		System.out.println("limit: -> "+bb.limit());
		
	}

	/**
	 * @Title: test1
	 * @Description: random access test
	 * @param @throws Exception
	 * @return void
	 * @author Tangyinbo
	 * @throws
	 */
	public static void test1() throws Exception {
		RandomAccessFile raf = new RandomAccessFile("G:/ws/aa.txt", "rw");
		FileChannel fc = raf.getChannel();
		ByteBuffer bf = ByteBuffer.allocate(48);
		int len = fc.read(bf);
		while (len != -1) {
			// System.out.println("position:->"+fc.position()+"\tsize:->"+fc.size());
			bf.flip();
			while (bf.hasRemaining()) {
				System.out.print((char) bf.get());
			}
			bf.clear();
			len = fc.read(bf);
		}
		raf.close();
	}
}

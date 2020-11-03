package gleb.threads;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Writer implements Runnable {
	String answer;
	SocketChannel socketChannel;

	public Writer(SocketChannel socketChannel, String answer) {
		this.socketChannel = socketChannel;
		this.answer = answer;
	}

	@Override
	public void run() {
		try {
			socketChannel.write(ByteBuffer.wrap(answer.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

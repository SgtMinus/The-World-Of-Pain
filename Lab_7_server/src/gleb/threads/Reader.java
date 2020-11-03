package gleb.threads;

import gleb.Message;
import static gleb.server.Main_Server.*;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Reader implements Runnable{
	SocketChannel socketChannel;
	Selector selector;

	public Reader(SocketChannel socketChannel, Selector selector) {
		this.socketChannel = socketChannel;
		this.selector = selector;
	}

	@Override
	public void run() {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		try {
			socketChannel.read(buffer);
			buffer.flip();
			buffer.clear();
			socketChannel.register(selector, SelectionKey.OP_WRITE);
			byte[] bytes = buffer.array();
			ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(bytes));
			Message message = (Message) is.readObject();
			handle.execute(new Handler(socketChannel, message));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

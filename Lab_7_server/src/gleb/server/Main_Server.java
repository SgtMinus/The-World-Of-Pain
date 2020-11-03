package gleb.server;

import gleb.database.VehicleDB;
import gleb.threads.Reader;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main_Server {

	public static ExecutorService read = Executors.newFixedThreadPool(10);
	public static ExecutorService handle = Executors.newCachedThreadPool();
	public static ExecutorService write = Executors.newFixedThreadPool(10);

	public static void main(String[] args) {
		try {
			VehicleDB.connect();
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.bind(new InetSocketAddress(8000));
			while (true) {
				Selector selector = Selector.open();
				serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
				selector.select();
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				for (SelectionKey key : selectionKeys) {
					if (key.isAcceptable()) {
						SocketChannel client = serverSocketChannel.accept();
						client.configureBlocking(false);
						client.register(selector, SelectionKey.OP_READ);
					}
				}
				selector.select();
				selectionKeys = selector.selectedKeys();
				for (SelectionKey key : selectionKeys) {
					if (key.isReadable()) {
						read.execute(new Reader((SocketChannel) key.channel(), selector));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

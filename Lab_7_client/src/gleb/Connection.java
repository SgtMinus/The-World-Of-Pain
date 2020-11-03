package gleb;

import gleb.frames.AuthFrame;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Connection {

	private static final VehicleList vehicleList = new VehicleList();
	public static int PORT = 8000;
	public static String HOST = "localhost";
	public static Scanner scanner = new Scanner(System.in);

	public static void connect() {
		new AuthFrame();
		/*while (true) {
			try {
				System.out.print("\r");
				System.out.print("Введите логин: ");
				String login = scanner.nextLine();
				System.out.print("Введите пароль: ");
				String password = scanner.nextLine();
				System.out.println(sendReadMessage(new Message(login, password, " ", " ")));
				while (true) {
					vehicleList.commandChoose(login, password);
				}
			} catch (Exception e) {
				System.err.print("Нет связи с сервером.\r");
				System.exit(0);
			}
		}*/
	}

	public static String sendReadMessage(Message message) throws IOException {
		Socket socket = new Socket(HOST, PORT);
		OutputStream os = socket.getOutputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(message);
		oos.close();
		os.write(baos.toByteArray());

		InputStreamReader isr = new InputStreamReader(socket.getInputStream());
		BufferedReader reader = new BufferedReader(isr);
		String answer = "";
		char readChar = (char) reader.read();
		answer += readChar;
		while (reader.ready()) {
			readChar = (char) reader.read();
			answer += readChar;
		}
		return answer;
	}
}

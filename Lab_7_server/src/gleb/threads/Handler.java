package gleb.threads;

import gleb.Message;
import gleb.database.UserDB;
import gleb.server.VehicleList;

import static gleb.server.Main_Server.*;

import java.nio.channels.SocketChannel;

import static gleb.database.Hasher.encryptPassword;

public class Handler implements Runnable {
	Message message;
	SocketChannel client;
	VehicleList vehicleList = new VehicleList();
	UserDB userDB = new UserDB();

	public Handler(SocketChannel client, Message message) {
		this.message = message;
		this.client = client;
	}

	@Override
	public void run() {
		try {
			String answer;
			if (!message.getCommand().equals("REGISTER") && userDB.users.containsKey(message.getLogin()) && encryptPassword(message.getPassword()).equals(userDB.users.get(message.getLogin()))) {
				System.out.println("Получено: " + message);
				String command = message.getCommand() + " " + message.getData();
				if (!command.equals("   ")) {
					answer = vehicleList.commandChoose(command, message.getLogin());
				} else {
					answer = "LOGIN";
				}
				write.execute(new Writer(client, answer));
			} else if (message  .getCommand().equals("REGISTER") && !userDB.users.containsKey(message.getLogin())) {
				answer = userDB.addUser(message.getLogin(), message.getPassword());
				write.execute(new Writer(client, answer));
			} else if (message.getCommand().equals("REGISTER") && userDB.users.containsKey(message.getLogin())) {
				write.execute(new Writer(client, "alreadyExist"));
			} else {
				write.execute(new Writer(client, "wrongLoginPassword"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


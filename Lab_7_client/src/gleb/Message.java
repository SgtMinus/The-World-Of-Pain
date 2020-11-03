package gleb;

import java.io.Serializable;

public class Message implements Serializable {
	private String login;
	private String password;
	private String command;
	private String data;

	public Message(String login, String password, String command, String data) {
		this.login = login;
		this.password = password;
		this.command = command;
		this.data = data;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCommand() {
		return command;
	}

	public String getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Message{" +
				"login='" + login + '\'' +
				", password='" + password + '\'' +
				", command='" + command + '\'' +
				", data='" + data + '\'' +
				'}';
	}
}

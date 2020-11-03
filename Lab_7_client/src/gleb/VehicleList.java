package gleb;

import gleb.commands.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Класс содержащий команды и коллекцию vehicles
 */
public class VehicleList {

	Message message = new Message("", "", "", "");

	private HashMap<String, Command> commandMap = new HashMap<>();


	public VehicleList() {
		commandMap.put("HELP", new Help());
		commandMap.put("INFO", new Info());
		commandMap.put("SHOW", new Show());
		commandMap.put("ADD", new Add());
		commandMap.put("UPDATE", new Update());
		commandMap.put("REMOVE_BY_ID", new Remove_by_id());
		commandMap.put("CLEAR", new Clear());
		commandMap.put("EXIT", new Exit());
		commandMap.put("ADD_IF_MAX", new Add_if_max());
		commandMap.put("REMOVE_GREATER", new Remove_greater());
		commandMap.put("REMOVE_LOWER", new Remove_lower());
		commandMap.put("GROUP_COUNTING_BY_NUMBER_OF_WHEELS", new Group_counting_by_number_of_wheels());
		commandMap.put("FILTER_GREATER_THAN_TYPE", new Filter_greater_than_type());
		commandMap.put("PRINT_FIELD_ASCENDING_NUMBER_OF_WHEELS", new Print_field_ascending_number_of_wheels());
		commandMap.put("EXECUTE_SCRIPT", new Execute_script());
	}

	public Message commandChoose(String login, String password) throws IOException {
		System.out.print("\nВведите команду: ");
		String command = new Scanner(System.in).nextLine();
		String[] strings = command.split(" ");
		try {
			command = strings[0].trim().toUpperCase();
		} catch (ArrayIndexOutOfBoundsException e) {
			command = " ";
		}
		String data = "";
		for (int i = 1; i < strings.length; i++) {
			data += strings[i] + " ";
		}
		if (commandMap.get(command) != null) {
			Command command_execute = commandMap.get(command);
			message = command_execute.execute(data);
		} else {
			message = new Message("", "", "WRONG_INPUT", " ");
		}
		message.setLogin(login);
		message.setPassword(password);
		if (message.getCommand().equalsIgnoreCase("exit")) {
			System.exit(0);
		}
		String answer = Connection.sendReadMessage(message);
		System.out.println(answer);
		return message;
	}
}

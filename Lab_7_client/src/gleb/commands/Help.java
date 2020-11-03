package gleb.commands;

import gleb.Message;

/**
 * Класс команды help
 */
public class Help extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public Message execute(String data) {
		String[] strings = data.split(" ");
		if (!strings[0].isEmpty()) {
			System.out.println("Команда введена неверно");
		} else {
			message = new Message("","", "HELP", " ");
		}
		return message;
	}
}

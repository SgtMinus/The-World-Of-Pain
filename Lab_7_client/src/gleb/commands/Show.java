package gleb.commands;

import gleb.Message;

/**
 * Класс команды show
 */
public class Show extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public Message execute(String data) {
		String[] strings = data.split(" ");
		if (!strings[0].isEmpty()) {
			System.out.println("Команда введена неверно");
		} else {
			message = new Message("","", "SHOW", " ");
		}
		return message;
	}
}

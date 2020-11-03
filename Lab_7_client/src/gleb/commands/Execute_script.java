package gleb.commands;

import gleb.Message;

/**
 * Класс команды execute_script
 */
public class Execute_script extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public Message execute(String data) {
		String[] strings = data.split(" ");
		if (strings.length != 1) {
			System.out.println("Команда введена неверно");
		} else {
			message = new Message("","", "EXECUTE_SCRIPT", strings[0]);
		}
		return message;
	}
}
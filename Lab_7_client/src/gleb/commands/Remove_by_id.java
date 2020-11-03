package gleb.commands;

import gleb.app.Check;
import gleb.Message;

/**
 * Класс команды remove_by_id
 */
public class Remove_by_id extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public Message execute(String data) {
		String[] strings = data.split(" ");
		if (strings.length != 1) {
			System.out.println("Команда введена неверно");
		} else if (strings[0].trim().isEmpty() || !Check.checkLong(strings[0].trim())) {
			System.out.println("Неправильный тип числа");
		} else {
			message = new Message("","", "REMOVE_BY_ID", strings[0]);
		}
		return message;
	}
}

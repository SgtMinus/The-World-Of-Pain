package gleb.commands;

import gleb.Message;

/**
 * Класс команды print_field_ascending_number_of_wheels
 */
public class Print_field_ascending_number_of_wheels extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public Message execute(String data) {
		String[] strings = data.split(" ");
		if (!strings[0].isEmpty()) {
			System.out.println("Команда введена неверно");
		} else {
			message = new Message("","", "PRINT_FIELD_ASCENDING_NUMBER_OF_WHEELS", " ");
		}
		return message;
	}
}

package gleb.commands;

import gleb.Message;

/**
 * Класс команды group_counting_by_number_of_wheels
 */
public class Group_counting_by_number_of_wheels extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public Message execute(String data) {
		String[] strings = data.split(" ");
		if (!strings[0].isEmpty()) {
			System.out.println("Команда введена неверно");
		} else {
			message = new Message("","", "GROUP_COUNTING_BY_NUMBER_OF_WHEELS", " ");
		}
		return message;
	}
}

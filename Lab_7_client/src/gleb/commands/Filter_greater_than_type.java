package gleb.commands;

import gleb.Message;

/**
 * Класс команды filter_greater_than_type
 */
public class Filter_greater_than_type extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public Message execute(String data) {
		String[] strings = data.split(" ");
		if (strings.length != 2) {
			System.out.println("Команда введена неверно");
		} else {
			String type = strings[0].trim();
			String number = strings[1].trim();
			if (!(type.equals("coordinates") || type.equals("enginePower") || type.equals("numberOfWheels"))) {
				System.out.println("Команда введена неверно");
			} else if (!number.matches("-?[0-9]*\\.?[0-9]+")) {
				System.out.println("Команда введена неверно");
			} else {
				message = new Message("","", "FILTER_GREATER_THAN_TYPE", type + " " + number);
			}
		}
		return message;
	}
}

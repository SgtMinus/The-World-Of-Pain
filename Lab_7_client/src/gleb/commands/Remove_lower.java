package gleb.commands;

import gleb.Message;


/**
 * Класс команды remove_greater
 */
public class Remove_lower extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public Message execute(String data) {
		Add add = new Add();
		String[] strings = data.split(" ");
		if (strings.length != 1) {
			System.out.println("Команда введена неверно");
		} else {
			String name = strings[0].trim();
			if (name.isEmpty()) {
				System.out.println("Вы не ввели имя");
			} else {
				message = new Message("","", "REMOVE_LOWER", name + " " + add.setX() + " " + add.setY() + " " + add.setEnginePower() + " " + add.setNumberOfWheels() + " " + add.setVehicleType() + " " + add.setFuelType());
			}
		}
		return message;
	}
}

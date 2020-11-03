package gleb.commands;

import gleb.Message;

/**
 * Класс команды add_if_max
 */
public class Add_if_max extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public Message execute(String data) {
		Add add = new Add();
		String[] strings = data.split(" ");
		String name = strings[0].trim();
		if (strings.length != 1) {
			System.out.println("Команда введена неверно");
		} else if (name.isEmpty()) {
			System.out.println("Вы не ввели имя");
		} else {
			message = new Message("", "", "ADD_IF_MAX", name + " " + add.setX() + " " + add.setY() + " " + add.setEnginePower() + " " + add.setNumberOfWheels() + " " + add.setVehicleType() + " " + add.setFuelType());
		}
		return message;
	}
}

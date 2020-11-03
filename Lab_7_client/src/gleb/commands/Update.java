package gleb.commands;

import gleb.app.Check;
import gleb.Message;

/**
 * Класс команды update
 */
public class Update extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public Message execute(String data) {
		Add add = new Add();
		String[] strings = data.split(" ");
		if (strings.length != 2) {
			System.out.println("Команда введена неверно");
		} else {
			if (strings[0].trim().isEmpty() || !Check.checkLong(strings[0].trim()) || strings[1].trim().isEmpty()) {
				System.out.println("Команда введена неверно");
			} else {
				String id = strings[0].trim();
				String name = strings[1].trim();
				message = new Message("","", "UPDATE", id + " " + name + " " + add.setX() + " " + add.setY() + " " + add.setEnginePower() + " " + add.setNumberOfWheels() + " " + add.setVehicleType() + " " + add.setFuelType());
			}
		}
		return message;
	}
}

package gleb.commands;

import gleb.database.VehicleDB;
import gleb.server.VehicleList;
import gleb.classes.Vehicle;

import java.util.Collections;

/**
 * Класс команды group_counting_by_number_of_wheels
 */
public class Group_counting_by_number_of_wheels extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data, String userCreator) {
		vehicleList.vehicles = Collections.synchronizedSet(VehicleDB.getCollection());
		int even = 0;
		int odd = 0;
		for (Vehicle vehicle : vehicleList.vehicles) {
			if (vehicle.getNumberOfWheels() % 2 == 0) {
				even++;
			} else {
				odd++;
			}
		}
		answer = "Транспорт с четным количеством колёс: " + even +
				"\nТранспорт с нечётным количеством колёс: " + odd;
	}
}


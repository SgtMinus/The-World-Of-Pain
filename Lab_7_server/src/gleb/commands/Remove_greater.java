package gleb.commands;

import gleb.database.VehicleDB;
import gleb.server.VehicleList;
import gleb.classes.Vehicle;
import gleb.enums.FuelType;
import gleb.enums.VehicleType;

import java.util.Collections;

/**
 * Класс команды remove_greater
 */
public class Remove_greater extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data, String userCreator) {
		vehicleList.vehicles = Collections.synchronizedSet(VehicleDB.getCollection());
		String[] strings = data.split(" ");
		Vehicle remove_greater = new Vehicle(strings[0], strings[1],
				strings[2], strings[3], strings[4], VehicleType.valueOf(strings[5].toUpperCase()), FuelType.valueOf(strings[6].toUpperCase()), userCreator);
		long collection_size = vehicleList.vehicles.size();
		if (VehicleDB.remove_greater(remove_greater)) {
			vehicleList.vehicles = Collections.synchronizedSet(VehicleDB.getCollection());
			if (vehicleList.vehicles.size() < collection_size) {
				answer = "Элементы удалены";
			} else {
				answer = "Элементов больше заданного нет";
			}
		}
	}
}

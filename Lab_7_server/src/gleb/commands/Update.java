package gleb.commands;

import gleb.database.VehicleDB;
import gleb.server.VehicleList;
import gleb.classes.Vehicle;
import gleb.enums.FuelType;
import gleb.enums.VehicleType;

import java.time.ZonedDateTime;
import java.util.Collections;

/**
 * Класс команды update
 */
public class Update extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data, String userCreator) {
		vehicleList.vehicles = Collections.synchronizedSet(VehicleDB.getCollection());
		String[] strings = data.split(" ");
		ZonedDateTime time = null;
		long id = Long.parseLong(strings[0].trim());
		for (Vehicle vehicle : vehicleList.vehicles) {
			if (id == vehicle.getID()) {
				time = vehicle.getCreationDate();
			}
		}
		if (vehicleList.vehicles.removeIf(vehicle -> vehicle.getID() == id)) {
			Vehicle update = new Vehicle(Long.parseLong(strings[0]), strings[1], strings[2], strings[3], time, strings[4], strings[5], VehicleType.valueOf(strings[6].toUpperCase()), FuelType.valueOf(strings[7].toUpperCase()), userCreator);
			if (VehicleDB.updateID(update)) {
				vehicleList.vehicles = Collections.synchronizedSet(VehicleDB.getCollection());
				answer = "Элемент обновлён";
			} else {
				answer = "Элемента с таким id нет";
			}
		} else {
			answer = "Элемента с таким id нет";
		}

	}
}

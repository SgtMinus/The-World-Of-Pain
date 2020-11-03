package gleb.commands;

import gleb.database.VehicleDB;
import gleb.server.VehicleList;
import gleb.classes.Vehicle;
import gleb.enums.FuelType;
import gleb.enums.VehicleType;

import java.util.Collections;

/**
 * Класс команды add
 */
public class Add extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data, String userCreator) {
		String[] strings = data.split(" ");
		try {
			Vehicle vehicle = new Vehicle(strings[0], strings[1],
					strings[2], strings[3], strings[4], VehicleType.valueOf(strings[5].toUpperCase()), FuelType.valueOf(strings[6].toUpperCase()), userCreator);
			if (VehicleDB.add(vehicle)) {
				vehicleList.vehicles = Collections.synchronizedSet(VehicleDB.getCollection());
				answer = "Элемент добавлен";
			}
		} catch (Exception e) {
			e.printStackTrace();
			answer = "Элемент не добавлен";
		}
	}
}

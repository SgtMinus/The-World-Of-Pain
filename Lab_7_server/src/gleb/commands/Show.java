package gleb.commands;

import gleb.classes.Vehicle;
import gleb.database.VehicleDB;
import gleb.server.VehicleList;

/**
 * Класс команды show
 */
public class Show extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data, String userCreator) {
		vehicleList.vehicles = VehicleDB.getCollection();
		if (vehicleList.vehicles.isEmpty()) {
			answer = "Коллекция пустая";
		} else {
			for (Vehicle vehicle : vehicleList.vehicles) {
				answer += vehicle;
			}
		}
	}
}
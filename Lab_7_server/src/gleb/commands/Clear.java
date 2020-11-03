package gleb.commands;

import gleb.database.VehicleDB;
import gleb.server.VehicleList;

import java.util.Collections;

/**
 * Класс команды clear
 */
public class Clear extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data, String userCreator) {
		vehicleList.vehicles = VehicleDB.getCollection();
		if (!vehicleList.vehicles.isEmpty()) {
			if (VehicleDB.clear(userCreator)) {
				vehicleList.vehicles = Collections.synchronizedSet(VehicleDB.getCollection());
				answer = "Коллекция очищена";
			}
		} else {
			answer = "Коллекция пуста";
		}
	}
}

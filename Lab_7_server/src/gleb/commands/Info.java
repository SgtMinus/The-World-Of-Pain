package gleb.commands;

import gleb.database.VehicleDB;
import gleb.server.VehicleList;

import java.util.Collections;

/**
 * Класс команды info
 */
public class Info extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data, String userCreator) {
		vehicleList.vehicles = Collections.synchronizedSet(VehicleDB.getCollection());
		answer = "Тип коллекции - HashSet\n" + vehicleList.vehicles.size() + " элементов в коллекции" + "\nДата инициализации: " + vehicleList.initializationDate;
	}
}

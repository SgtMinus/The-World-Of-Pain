package gleb.commands;

import gleb.database.VehicleDB;
import gleb.server.VehicleList;

import java.util.Collections;

/**
 * Класс команды remove_by_id
 */
public class Remove_by_id extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data, String userCreator) {
		vehicleList.vehicles = Collections.synchronizedSet(VehicleDB.getCollection());
		try {
			String[] strings = data.split(" ");
			long id = Long.parseLong(strings[0].trim());
			if (VehicleDB.remove_by_id(id, userCreator)) {
				vehicleList.vehicles = Collections.synchronizedSet(VehicleDB.getCollection());
				answer = "Элемент удалён";
			} else {
				answer = "Элемента с таким id нет, либо элемент был создан не вами";
			}
		} catch (Exception e) {
			System.out.println("Exception");
		}
	}
}

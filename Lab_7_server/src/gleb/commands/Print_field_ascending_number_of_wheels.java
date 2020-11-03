package gleb.commands;

import gleb.database.VehicleDB;
import gleb.server.VehicleList;
import gleb.classes.NumberOfWheelsComparator;
import gleb.classes.Vehicle;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс команды print_field_ascending_number_of_wheels
 */
public class Print_field_ascending_number_of_wheels extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data, String userCreator) {
		vehicleList.vehicles = Collections.synchronizedSet(VehicleDB.getCollection());
		ArrayList<Vehicle> arrayList = new ArrayList<>(vehicleList.vehicles);
		arrayList.sort(new NumberOfWheelsComparator());
		if (!arrayList.isEmpty()) {
			for (Vehicle vehicle : arrayList) {
				answer = "У " + vehicle.getName() + " " + vehicle.getNumberOfWheels() + " колёс";
			}
		} else {
			answer = "Коллекция пуста";
		}
	}
}

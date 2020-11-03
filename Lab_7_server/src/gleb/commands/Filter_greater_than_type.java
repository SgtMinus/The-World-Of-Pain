package gleb.commands;

import gleb.database.VehicleDB;
import gleb.server.VehicleList;
import gleb.classes.Vehicle;

import java.util.Collections;

/**
 * Класс команды filter_greater_than_type
 */
public class Filter_greater_than_type extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data, String userCreator) {
		vehicleList.vehicles = Collections.synchronizedSet(VehicleDB.getCollection());
		String[] strings = data.split(" ");
		String type = strings[0].trim();
		String number = strings[1].trim();
		try{

		switch (type) {
			case ("Координаты"):
			case ("Coordenadas"):
			case ("Koordinatės"):
			case ("Coordonatele"):
				for (Vehicle vehicle : vehicleList.vehicles) {
					if (Float.parseFloat(number) < vehicle.getCoordinates()) {
						answer += "ID = " + vehicle.getID() + "Имя = " + vehicle.getName() + "\n";
					}
				}
				if (answer.equals("")) answer = "Таких элементов нет";
				break;
			case ("Мощность"):
			case ("Poder"):
			case ("Galia"):
			case ("Putere"):
				for (Vehicle vehicle : vehicleList.vehicles) {
					if (Float.parseFloat(number) < vehicle.getEnginePower()) {
						answer += "ID = " + vehicle.getID() + " Имя = " + vehicle.getName() + "\n";
					}
				}
				if (answer.equals("")) answer = "Таких элементов нет";
				break;
			case ("Колеса"):
			case ("Ruedas"):
			case ("Ratai"):
			case ("Roți"):
				for (Vehicle vehicle : vehicleList.vehicles) {
					if (Float.parseFloat(number) < vehicle.getNumberOfWheels()) {
						answer += "ID = " + vehicle.getID() + "; Имя = " + vehicle.getName() + "\n";
					}
				}
				if (answer.equals("")) answer = "Таких элементов нет";
				break;
			case ("Имя"):
			case ("Ім\'я"):
			case ("Nume"):
			case ("Nombre"):
				for (Vehicle vehicle : vehicleList.vehicles) {
					if (vehicle.getName().compareTo(number)>=0) {
						answer += "ID = " + vehicle.getID() + "; Имя = " + vehicle.getName() + "\n";
					}
				}
				if (answer.equals("")) answer = "Таких элементов нет";
				break;

			case ("Транспорт"):
			case ("Transporte"):
			case ("Transport"):
				for (Vehicle vehicle : vehicleList.vehicles) {
					if (vehicle.getType().toString().compareTo(number)>=0) {
						answer += "ID = " + vehicle.getID() + "; Имя = " + vehicle.getName() + "\n";
					}
				}
				if (answer.equals("")) answer = "Таких элементов нет";
				break;

			case ("Топливо"):
			case ("Combustibil"):
			case ("Combustible"):
			case ("Паливо"):
				for (Vehicle vehicle : vehicleList.vehicles) {
					if (vehicle.getFuelType().toString().compareTo(number)>=0) {
						answer += "ID = " + vehicle.getID() + "; Имя = " + vehicle.getName() + "\n";
					}
				}
				if (answer.equals("")) answer = "Таких элементов нет";
				break;

			default:
				answer = "Такого поля нет";
		}} catch (Exception e) {answer = "Неправильный ввод";}
	}
}
package gleb.commands;

import gleb.server.VehicleList;

/**
 * Интерфейс команд
 */
public interface ICommand {
	void execute(VehicleList vehicleList, String data, String userCreator);
}

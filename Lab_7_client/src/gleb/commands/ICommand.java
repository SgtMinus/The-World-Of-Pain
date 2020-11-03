package gleb.commands;

import gleb.Message;

/**
 * Интерфейс команд
 */
public interface ICommand {
	Message execute(String data);
}

package gleb.commands;

import gleb.Message;

/**
 * Абстрактный класс команд
 */
public abstract class Command implements ICommand {
	public Message message = new Message("","", "0", " ");
}

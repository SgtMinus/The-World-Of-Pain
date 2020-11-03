package gleb.commands;

/**
 * Абстрактный класс команд
 */
public abstract class Command implements ICommand {
	public String answer = "";

	public String getAnswer() {
		return answer;
	}
}

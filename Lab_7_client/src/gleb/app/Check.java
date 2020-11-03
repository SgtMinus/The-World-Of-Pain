package gleb.app;

/**
 * Класс для проверки строк
 */
public class Check {

	/**
	 * Проверяем, является ли строка числом типа int
	 *
	 * @param number число, которое надо проверить
	 * @return Возвращает true, если число типа int
	 */
	public static boolean checkInt(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Проверяем, является ли строка числом типа float
	 *
	 * @param number число, которое надо проверить
	 * @return Возвращает true, если число типа float
	 */
	public static boolean checkFloat(String number) {
		try {
			Float.parseFloat(number);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Проверяем, является ли число типа long
	 *
	 * @param number число, которое надо проверить
	 * @return Возвращает true, если число типа long
	 */
	public static boolean checkLong(String number) {
		try {
			Long.parseLong(number);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Проверяем, является ли число положительным
	 *
	 * @param number число, которое надо проверить
	 * @return Возвращает true, если число положительное
	 */
	public static boolean checkPositive(String number) {
		return number.matches("\\d*\\.?\\d+");
	}

	/**
	 * Проверяем, больше ли число -615
	 *
	 * @param number число, которое надо проверить
	 * @return Возвращает true, если число больше -615
	 */
	public static boolean checkMoreThan_minus_615(String number) {
		try {
			return Float.parseFloat(number) > -615;
		} catch (Exception e) {
			return false;
		}
	}
}

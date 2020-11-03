package gleb.classes;

/**
 * Класс координат
 */
public class Coordinates {

	/**
	 * Координата "x"
	 */
	private final float x; //Значение поля должно быть больше -615

	/**
	 * Координата "y"
	 */
	private final Float y; //Поле не может быть null

	/**
	 * Конструктор
	 *
	 * @param x координата
	 * @param y координата
	 */
	public Coordinates(String x, String y) {
		this.x = Float.parseFloat(x);
		this.y = Float.parseFloat(y);
	}

	/**
	 * Метод для получения координаты "x"
	 *
	 * @return Возвращает координату "x"
	 */
	public float getX() {
		return x;
	}

	/**
	 * Метод для получения координаты "y"
	 *
	 * @return Возвращает координату "y"
	 */
	public float getY() {
		return y;
	}

}
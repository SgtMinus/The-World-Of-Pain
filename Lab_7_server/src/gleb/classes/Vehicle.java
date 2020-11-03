package gleb.classes;

import gleb.enums.FuelType;
import gleb.enums.VehicleType;

import java.time.ZonedDateTime;

/**
 * Класс транспорта
 */
public class Vehicle implements Comparable<Vehicle> {

	private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
	private String name; //Поле не может быть null, Строка не может быть пустой
	private Coordinates coordinates; //Поле не может быть null
	private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
	private Integer enginePower; //Поле не может быть null, Значение поля должно быть больше 0
	private long numberOfWheels; //Значение поля должно быть больше 0
	private VehicleType type; //Поле может быть null
	private FuelType fuelType; //Поле не может быть null
	private String userCreator;

	public Vehicle(String name, String x, String y, String enginePower, String numberOfWheels, VehicleType type, FuelType fuelType, String userCreator) {
		this.name = name;
		this.coordinates = new Coordinates(x, y);
		creationDate = ZonedDateTime.now();
		this.enginePower = Integer.parseInt(enginePower);
		this.numberOfWheels = Long.parseLong(numberOfWheels);
		this.type = type;
		this.fuelType = fuelType;
		this.userCreator = userCreator;
	}

	public Vehicle(Long id, String name, String x, String y, ZonedDateTime creationDate, String enginePower, String numberOfWheels, VehicleType type, FuelType fuelType, String userCreator) {
		this.id = id;
		this.name = name;
		this.coordinates = new Coordinates(x, y);
		this.creationDate = creationDate;
		this.enginePower = Integer.parseInt(enginePower);
		this.numberOfWheels = Long.parseLong(numberOfWheels);
		this.type = type;
		this.fuelType = fuelType;
		this.userCreator = userCreator;
	}

	/**
	 * Метод для получения значения поля {@link Vehicle#name}
	 *
	 * @return Возвращает название транспорта
	 */
	public String getName() {
		return name;
	}

	/**
	 * Метод для получения значения поля {@link Vehicle#id}
	 *
	 * @return Возвращает ID объекта
	 */
	public long getID() {
		return id;
	}

	/**
	 * Метод для получения размера объекта ({@link Vehicle#enginePower} складывается с {@link Vehicle#numberOfWheels})
	 *
	 * @return Возвращает размер объекта
	 */
	public long getSize() {
		return enginePower + numberOfWheels;
	}

	/**
	 * Метод для получения значения поля {@link Vehicle#enginePower}
	 *
	 * @return Возвращает мощность объекта
	 */
	public int getEnginePower() {
		return enginePower;
	}

	/**
	 * Метод для получения значения поля {@link Vehicle#coordinates}
	 *
	 * @return Возвращает координаты объекта
	 */
	public float getCoordinates() {
		return coordinates.getX() + coordinates.getY();
	}

	public float getX() {
		return coordinates.getX();
	}

	public float getY() {
		return coordinates.getY();
	}

	/**
	 * Метод для получения значения поля {@link Vehicle#creationDate}
	 *
	 * @return Возвращает время создания объекта
	 */
	public ZonedDateTime getCreationDate() {
		return creationDate;
	}

	/**
	 * Метод для получения значения поля {@link Vehicle#numberOfWheels}
	 *
	 * @return Возвращает кол-во колес объекта
	 */
	public Long getNumberOfWheels() {
		return numberOfWheels;
	}

	/**
	 * Метод для получения значения поля {@link Vehicle#type}
	 *
	 * @return Возвращает тип объекта
	 */
	public VehicleType getType() {
		return type;
	}

	/**
	 * Метод для получения значения поля {@link Vehicle#fuelType}
	 *
	 * @return Возвращает тип топлива объекта
	 */
	public FuelType getFuelType() {
		return fuelType;
	}

	/**
	 * Метод для получения информации об объекте
	 *
	 * @return Возвращает информацию об объекте
	 */
	@Override
	public String toString() {
		return getID() + " " + getName() + " " + getX() + " " + getY() + " " + getCreationDate() + " " + getEnginePower() + " " + getNumberOfWheels() + " " + getType() + " " + getFuelType() + " " + getUserCreator() + "\n";
	}

	public String getUserCreator() {
		return userCreator;
	}

	@Override
	public int compareTo(Vehicle anotherVehicle) {
		if (getSize() > anotherVehicle.getSize()) {
			return 1;
		} else if (getSize() == anotherVehicle.getSize()) {
			return 0;
		} else {
			return -1;
		}
	}
}

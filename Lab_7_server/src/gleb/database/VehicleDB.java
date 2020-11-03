package gleb.database;

import gleb.classes.Vehicle;
import gleb.enums.FuelType;
import gleb.enums.VehicleType;
import gleb.server.VehicleList;

import java.sql.*;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashSet;


public class VehicleDB {

	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	//	private static final String URL = "jdbc:postgresql://pg:5432/studs";
	private static final String USER = "postgres";
	//	private static final String USER = "s285569";
	private static final String PASSWORD = "p32323";
	//	private static final String PASSWORD = "\[T]/";
	static Connection connection;
	public static VehicleList vehicleList = new VehicleList();

	public static void connect() {
		try {
			System.out.println("Подключение к базе данных");
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("База данных подключена");
			vehicleList.vehicles = Collections.synchronizedSet(createCollection(connection));
		} catch (Exception e) {
			System.out.println("Не удалось подключиться к базе данных vehicle");
		}
	}

	private static HashSet<Vehicle> createCollection(Connection connection) throws SQLException {
		HashSet<Vehicle> vehicles = new HashSet<>();
		String create = "CREATE TABLE IF NOT EXISTS vehicle (" +
				"id bigserial," +
				"name text," +
				"x real," +
				"y real," +
				"creationdate text," +
				"enginepower int," +
				"numberofwheels bigint," +
				"vehicletype text," +
				"fueltype text," +
				"usercreator text" +
				");";
		PreparedStatement ps = connection.prepareStatement(create);
		ps.execute();
		String SELECT = "SELECT * FROM vehicle";
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Vehicle vehicle = new Vehicle(
					Long.parseLong(resultSet.getString("id")),
					resultSet.getString("name"),
					resultSet.getString("x"),
					resultSet.getString("y"),
					ZonedDateTime.parse(resultSet.getString("creationdate")),
					resultSet.getString("enginepower"),
					resultSet.getString("numberofwheels"),
					VehicleType.valueOf(resultSet.getString("vehicletype")),
					FuelType.valueOf(resultSet.getString("fueltype")),
					resultSet.getString("userCreator"));
			vehicles.add(vehicle);
		}
		return vehicles;
	}

	public static HashSet<Vehicle> getCollection() {
		HashSet<Vehicle> vehicleHashSet = new HashSet<>();
		try {
			String SELECT = "SELECT * FROM vehicle";
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Vehicle vehicle = new Vehicle(
						Long.parseLong(resultSet.getString("id")),
						resultSet.getString("name"),
						resultSet.getString("x"),
						resultSet.getString("y"),
						ZonedDateTime.parse(resultSet.getString("creationdate")),
						resultSet.getString("enginepower"),
						resultSet.getString("numberofwheels"),
						VehicleType.valueOf(resultSet.getString("vehicletype")),
						FuelType.valueOf(resultSet.getString("fueltype")),
						resultSet.getString("userCreator"));
				vehicleHashSet.add(vehicle);
			}
			return vehicleHashSet;
		} catch (SQLException e) {
			return vehicleHashSet;
		}
	}

	public static boolean add(Vehicle vehicle) {
		try {
			String name = vehicle.getName();
			float x = vehicle.getX();
			float y = vehicle.getY();
			String creationTime = vehicle.getCreationDate().toString();
			int enginePower = vehicle.getEnginePower();
			long numberOfWheels = vehicle.getNumberOfWheels();
			String vehicleType = vehicle.getType().toString();
			String fuelType = vehicle.getFuelType().toString();
			String userCreator = vehicle.getUserCreator();
			PreparedStatement ps = connection.prepareStatement("INSERT INTO vehicle (name, x, y, creationdate, enginepower, numberofwheels, vehicletype, fueltype, usercreator) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, name);
			ps.setFloat(2, x);
			ps.setFloat(3, y);
			ps.setString(4, creationTime);
			ps.setInt(5, enginePower);
			ps.setLong(6, numberOfWheels);
			ps.setString(7, vehicleType);
			ps.setString(8, fuelType);
			ps.setString(9, userCreator);
			ps.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean add_if_max(Vehicle add_if_max) {
		try {
			HashSet<Vehicle> vehicles = getCollection();
			String name = add_if_max.getName();
			float x = add_if_max.getX();
			float y = add_if_max.getY();
			String creationTime = add_if_max.getCreationDate().toString();
			int enginePower = add_if_max.getEnginePower();
			long numberOfWheels = add_if_max.getNumberOfWheels();
			String vehicleType = add_if_max.getType().toString();
			String fuelType = add_if_max.getFuelType().toString();
			String userCreator = add_if_max.getUserCreator();
			if (!vehicles.isEmpty() && add_if_max.compareTo(Collections.max(vehicles)) <= 0) {
				return false;
			} else {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO vehicle (name, x, y, creationdate, enginepower, numberofwheels, vehicletype, fueltype, usercreator) " +
						"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, name);
				ps.setFloat(2, x);
				ps.setFloat(3, y);
				ps.setString(4, creationTime);
				ps.setInt(5, enginePower);
				ps.setLong(6, numberOfWheels);
				ps.setString(7, vehicleType);
				ps.setString(8, fuelType);
				ps.setString(9, userCreator);
				ps.execute();
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean clear(String userCreator) {
		try {
			PreparedStatement ps = connection.prepareStatement("delete from vehicle where usercreator=?");
			ps.setString(1, userCreator);
			ps.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean remove_by_id(long id, String userCreator) {
		try {
			PreparedStatement ps = connection.prepareStatement("delete from vehicle where id=? and usercreator=?");
			ps.setLong(1, id);
			ps.setString(2, userCreator);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean remove_greater(Vehicle vehicle) {
		try {
			PreparedStatement ps = connection.prepareStatement("delete from vehicle where numberofwheels+enginepower > ? and usercreator = ?;");
			ps.setLong(1, vehicle.getSize());
			ps.setString(2, vehicle.getUserCreator());
			ps.execute();
			add(vehicle);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean remove_lower(Vehicle vehicle) {
		try {
			PreparedStatement ps = connection.prepareStatement("delete from vehicle where numberofwheels+enginepower < ? and usercreator = ?;");
			ps.setLong(1, vehicle.getSize());
			ps.setString(2, vehicle.getUserCreator());
			ps.execute();
			add(vehicle);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean updateID(Vehicle vehicle) {
		try {
			long id = vehicle.getID();
			String name = vehicle.getName();
			float x = vehicle.getX();
			float y = vehicle.getY();
			String creationTime = vehicle.getCreationDate().toString();
			int enginePower = vehicle.getEnginePower();
			long numberOfWheels = vehicle.getNumberOfWheels();
			String vehicleType = vehicle.getType().toString();
			String fuelType = vehicle.getFuelType().toString();
			String userCreator = vehicle.getUserCreator();
			PreparedStatement ps = connection.prepareStatement("update vehicle set " +
					"(name, x, y, creationdate, enginepower, numberofwheels, vehicletype, fueltype) " +
					"= (?, ?, ?, ?, ?, ?, ?, ?) where id=? and usercreator=?");
			ps.setString(1, name);
			ps.setFloat(2, x);
			ps.setFloat(3, y);
			ps.setString(4, creationTime);
			ps.setInt(5, enginePower);
			ps.setLong(6, numberOfWheels);
			ps.setString(7, vehicleType);
			ps.setString(8, fuelType);
			ps.setLong(9, id);
			ps.setString(10, userCreator);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}

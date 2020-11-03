package gleb.database;

import java.sql.*;
import java.util.HashMap;

import static gleb.database.Hasher.*;

public class UserDB {

	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
//	private static final String URL = "jdbc:postgresql://pg:5432/studs";
	private static final String USER = "postgres";
//	private static final String USER = "s285569";
	private static final String PASSWORD = "p32323";
//	private static final String PASSWORD = "\[T]/";
	private static Connection connection;
	public HashMap<String, String> users = new HashMap<>();

	public UserDB() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			users = getUsers(connection);
		} catch (Exception e) {
			System.out.println("Не удалось подключиться к базе данных пользователей");
		}
	}

	static HashMap<String, String> getUsers(Connection connection) throws SQLException {
		HashMap<String, String> users = new HashMap<>();
		String create = "CREATE TABLE IF NOT EXISTS users (" +
				"login text UNIQUE," +
				"password text" +
				");";
		PreparedStatement ps = connection.prepareStatement(create);
		ps.execute();
		String SELECT = "SELECT * FROM users";
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			users.put(resultSet.getString("login"), resultSet.getString("password"));
		}
		return users;
	}

	public String addUser(String login, String password) {
		try {
			String add_user = "insert into users values (?, ?)";
			PreparedStatement ps = connection.prepareStatement(add_user);
			ps.setString(1, login);
			ps.setString(2, encryptPassword(password));
			ps.execute();
			users.put(login, encryptPassword(password));
			return "REGISTER";
		} catch (SQLException e) {
			return "Пользователь с таким логином уже существует";
		}
	}
}

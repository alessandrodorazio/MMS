package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

	public static Connection connection;

	public static Connection connect() {
		if (connection != null)
			return connection;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/MMS?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Rome",
					"root", "ciurialecs");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean getStatus() {
		if (connection != null)
			return true;
		return false;
	}

	public static String getStatusString() {
		if (connection != null)
			return "Connesso";
		return "Non connesso";
	}

}

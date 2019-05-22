package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	
	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	
	public static Connection connection;
	
	public static Connection connect() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/Motorway?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Rome", "root", "ciurialecs");
			return connection;
		}
		catch ( SQLException e ) {
			 e.printStackTrace();
		}
		return null;
	}
	
}

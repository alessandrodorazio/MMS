/**
 * 
 */
package tollbooth;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import helper.JDBC;

/**
 * @author alessandrodorazio
 *
 */
public class TollboothController {
	
	public static LinkedList<Tollbooth> index() {
		LinkedList<Tollbooth> tollbooths = new LinkedList<Tollbooth>();
		try {
			Statement statement = JDBC.connect().createStatement();
			ResultSet resultSet = statement.executeQuery( "SELECT * FROM Vehicle" );
			while(resultSet.next() ) {
				tollbooths.add( new Tollbooth(resultSet.getString("name"), resultSet.getInt("km")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tollbooths;
	}
	
	public static int create(Tollbooth tollbooth) {
		//if exist return error
		//create model
		//send data
		//TODO
		return 1;
	}
	
	public static void store(Tollbooth tollbooth) {
		try {
			PreparedStatement ps = JDBC.connect().prepareStatement("INSERT INTO Tollbooth (Name,Km) VALUES(?,?)");
			ps.setString(1, tollbooth.getName());
			ps.setInt(2, tollbooth.getKm());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void update(Tollbooth tollbooth) {
		try {
			PreparedStatement ps = JDBC.connect().prepareStatement("UPDATE Tollbooth SET name=?, km=? WHERE id=?");
			ps.setString(1, tollbooth.getName());
			ps.setInt(2, tollbooth.getKm());
			ps.setInt(3, tollbooth.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(int id) {
		try {
			JDBC.connect().prepareStatement("DELETE FROM Tollbooth WHERE id='" + id + "';").execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

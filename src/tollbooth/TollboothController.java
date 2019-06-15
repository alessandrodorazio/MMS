/**
 * 
 */
package tollbooth;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
			ResultSet resultSet = statement.executeQuery( "SELECT * FROM Tollbooth ORDER BY name ASC" );
			while(resultSet.next() ) {
				tollbooths.add( new Tollbooth(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("km")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tollbooths;
	}
	
	public static ArrayList<String> indexName() {
		ArrayList<String> tollbooths = new ArrayList<String>();
		try {
			Statement statement = JDBC.connect().createStatement();
			ResultSet resultSet = statement.executeQuery( "SELECT name FROM Tollbooth ORDER BY name ASC" );
			while(resultSet.next() ) {
				tollbooths.add( resultSet.getString("name") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tollbooths;
	}
	
	public static void create(String name, int km) {
		try {
			PreparedStatement ps = JDBC.connect().prepareStatement("INSERT INTO Tollbooth(name,km) values(?,?)");
			ps.setString(1, name);
			ps.setString(2, String.valueOf(km));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Tollbooth show(int id) {
		Tollbooth t = null;
		try {
			Statement statement = JDBC.connect().createStatement();
			ResultSet resultSet = statement.executeQuery( "SELECT * FROM Tollbooth WHERE id=" + id );
			while(resultSet.next() ) {
				t = new Tollbooth(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("km"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public static Tollbooth show(String name) {
		Tollbooth t = null;
		try {
			Statement statement = JDBC.connect().createStatement();
			ResultSet resultSet = statement.executeQuery( "SELECT * FROM Tollbooth WHERE name='" + name + "'");
			while(resultSet.next() ) {
				t = new Tollbooth(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("km"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
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

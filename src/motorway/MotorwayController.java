/**
 * 
 */
package motorway;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import helper.JDBC;

/**
 * @author alessandrodorazio
 *
 */
public class MotorwayController {

	public static Motorway create(String name, int reform, Map<String, Float> unitRate) {
		//TODO
		int count = 0;
		try {
			Statement statement = JDBC.connect().createStatement();
			ResultSet resultSet = statement.executeQuery( "SELECT count(*) AS total FROM Motorway" );
			while(resultSet.next() ) count = resultSet.getInt("total");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(count > 0) ;//TODO exception
		
		try {
			PreparedStatement ps = JDBC.connect().prepareStatement("INSERT INTO Motorway (name, reform) VALUES(?,?)");
			ps.setString(1, name);
			ps.setInt(2, reform);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps = JDBC.connect().prepareStatement("INSERT INTO UnitRate (category, rate) VALUES('A',?), ('B', ?), ('3', ?), ('4', ?), ('5', ?)");
			ps.setFloat(1, unitRate.get("A"));
			ps.setFloat(2, unitRate.get("B"));
			ps.setFloat(3, unitRate.get("3"));
			ps.setFloat(4, unitRate.get("4"));
			ps.setFloat(5, unitRate.get("5"));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Motorway.getInstance();
		
	}
	
	public static Motorway show() { 
		Motorway motorway = Motorway.getInstance();
		return motorway;
	}
	
	public static void update() { 
		//TODO
		//Prepared statement
		//PreparedStatement ps = JDBC.connect().prepareStatement("UPDATE Tollbooth SET name=?, km=? WHERE id=?");
		//UPDATE UnitRate SET rate=? WHERE category=?
		try {
			PreparedStatement ps = JDBC.connect().prepareStatement("UPDATE Motorway SET name=?, reform=? WHERE id=?");
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

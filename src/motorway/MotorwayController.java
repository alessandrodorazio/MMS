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
import tollbooth.Tollbooth;
import vehicle.Vehicle;

/**
 * @author alessandrodorazio
 *
 */
public class MotorwayController {

	public static boolean create(String name, Map<String, Float> unitRates, int type) {
		
		int count = 0;
		try {
			Statement statement = JDBC.connect().createStatement();
			ResultSet resultSet = statement.executeQuery( "SELECT count(*) AS total FROM Motorway" );
			while(resultSet.next() ) count = resultSet.getInt("total");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(count > 0) {
			return false; //TODO already exist
		}
		
		try {
			PreparedStatement ps = JDBC.connect().prepareStatement("INSERT INTO Motorway (name, type) VALUES(?, ?)");
			ps.setString(1, name);
			ps.setInt(2, type);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps = JDBC.connect().prepareStatement("INSERT INTO UnitRate (category, rate) VALUES('A',?), ('B', ?), ('3', ?), ('4', ?), ('5', ?)");
			ps.setFloat(1, unitRates.get("A"));
			ps.setFloat(2, unitRates.get("B"));
			ps.setFloat(3, unitRates.get("3"));
			ps.setFloat(4, unitRates.get("4"));
			ps.setFloat(5, unitRates.get("5"));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps = JDBC.connect().prepareStatement("INSERT INTO UnitRate (category, rate) VALUES('E1',?), ('E2', ?), ('E3', ?), ('E4', ?), ('E5', ?), ('E6', ?)");
			ps.setFloat(1, unitRates.get("E1"));
			ps.setFloat(2, unitRates.get("E2"));
			ps.setFloat(3, unitRates.get("E3"));
			ps.setFloat(4, unitRates.get("E4"));
			ps.setFloat(5, unitRates.get("E5"));
			ps.setFloat(6, unitRates.get("E6"));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Motorway.refresh();
		
		return true;
		
	}
	
	//TODO CREATE AFTER REFORM (WITH E1-E6)
	
	public static Motorway show() { 
		Motorway motorway = Motorway.getInstance();
		return motorway;
	}
	
	//TODO UPDATE UNIT RATES
	
	public static float tollCalc(Vehicle v, Tollbooth in, Tollbooth out) {
		Toll toll = new Toll(v, in, out);
		try {
			PreparedStatement ps = JDBC.connect().prepareStatement("INSERT INTO Toll (vehicle_id, tollbooth_in, tollbooth_out, cost) VALUES(?,?,?,?)");
			ps.setString(1, v.getPlateNumber());
			ps.setInt(2, in.getId());
			ps.setInt(3, out.getId());
			ps.setFloat(4, toll.cost);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toll.cost;
	}
	
	
}

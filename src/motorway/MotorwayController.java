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
			ResultSet resultSet = statement.executeQuery("SELECT count(*) AS total FROM Motorway");
			while (resultSet.next())
				count = resultSet.getInt("total");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (count > 0) {
			return false; // TODO already exist
		}

		try {
			PreparedStatement ps = JDBC.connect().prepareStatement("INSERT INTO Motorway (name, type) VALUES(?, ?)");
			ps.setString(1, name);
			ps.setInt(2, type);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		unitRates.forEach((k,v)->{
			try {
				PreparedStatement ps = JDBC.connect().prepareStatement(
						"INSERT INTO UnitRate (category, rate) VALUES (?,?)");
				ps.setString(1, k);
				ps.setFloat(2, v);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});	

		Motorway.refresh();

		return true;

	}
	
	

	public static Motorway show() {
		Motorway motorway = Motorway.getInstance();
		return motorway;
	}
	
	public static void updateUnitRates(Map<String, Float> unitRates) {
		unitRates.forEach((k,v)->{
			try {
				PreparedStatement ps = JDBC.connect().prepareStatement(
						"UPDATE UnitRate SET rate=? WHERE category=?");
				ps.setFloat(1, v);
				ps.setString(2, k);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});	
		Motorway.refresh();
	}

	public static float tollCalc(Vehicle v, Tollbooth in, Tollbooth out) {
		Toll toll = new Toll(v, in, out);
		try {
			PreparedStatement ps = JDBC.connect().prepareStatement(
					"INSERT INTO Toll (vehicle_id, tollbooth_in, tollbooth_out, cost) VALUES(?,?,?,?)");
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

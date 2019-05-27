/**
 * 
 */
package motorway;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import helper.JDBC;
import vehicle.Vehicle;

/**
 * @author alessandrodorazio
 *
 */

public final class Motorway {

	private int id;
	public int reform = 0; //1 = 2021, 2 = 2026

	private static Motorway motorway = new Motorway();
	
	private String name;
	private Map<String, Float> unitRate;
	
	private Motorway() {
		this.unitRate = new HashMap<String, Float>();
		try {
			Statement statement = JDBC.connect().createStatement();
			ResultSet resultSet = statement.executeQuery( "SELECT id, name, reform FROM Motorway LIMIT 1" );
			while(resultSet.next() ) {
				this.id = resultSet.getInt("id");
				this.name = resultSet.getString("name");
				this.reform = resultSet.getInt("reform");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Statement statement = JDBC.connect().createStatement();
			ResultSet resultSet = statement.executeQuery( "SELECT * FROM UnitRate" );
			while(resultSet.next() ) {
				this.unitRate.put(resultSet.getString("category"), resultSet.getFloat("rate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public String getName() { return motorway.name; }
	protected void setName(String name) { motorway.name = name; }

	public Map<String, Float> getUnitRate() { return motorway.unitRate; }
	protected void setUnitRate(Map<String, Float> unitRate) { motorway.unitRate = unitRate; }
	public float getUnitRateSingle(Vehicle v) { return motorway.unitRate.get(v.getUnitRate()); }
	
	public static final Motorway getInstance() { return motorway; }
	
	public int getReformState() { return motorway.reform; }
	public void setReformState(int r) { motorway.reform = r; }
	
}

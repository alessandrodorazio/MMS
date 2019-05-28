/**
 * 
 */
package vehicle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import helper.JDBC;
import motorway.Motorway;
import motorway.Toll;
import tollbooth.Tollbooth;
import vehicle.Vehicle.BuildVehicle;

/**
 * @author alessandrodorazio
 *
 */
public class VehicleController {

	
	public static LinkedList<Vehicle> index() { 
		LinkedList<Vehicle> vehicles = new LinkedList<Vehicle>();
		try {
			Statement statement = JDBC.connect().createStatement();
			ResultSet resultSet = statement.executeQuery( "SELECT * FROM vehicle" );
			while(resultSet.next() ) {
				vehicles.add( new Vehicle.BuildVehicle(resultSet.getString("plate_number"))
						.withBrandModel(resultSet.getString("model"), resultSet.getString("brand"))
						.withYear(resultSet.getInt("year"))
						.withAxis(resultSet.getInt("axis"))
						.withWeight(resultSet.getInt("weight"))
						.withHeight(resultSet.getInt("height"))
						.build()
				);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return vehicles;
	}
	
	//constructor before reform
	public static Vehicle create(String brand, String model, String plateNumber, int year, int axis,
			int weight, int height) {
		BuildVehicle builder = new Vehicle.BuildVehicle(plateNumber)
				.withBrandModel(model, brand)
				.withYear(year)
				.withAxis(axis)
				.withWeight(weight)
				.withHeight(height);
		Vehicle vehicle = builder.build();
		store(vehicle);		
		return vehicle;
		
	}
	
	//constructor after reform
	public static Vehicle create(String brand, String model, String plateNumber, char environmentalClass, int year, int axis,
			int weight, int height, int noisePollution) { 
		BuildVehicle builder = new Vehicle.BuildVehicle(plateNumber)
				.withBrandModel(model, brand)
				.withEnvironmentalClass(environmentalClass)
				.withYear(year).withAxis(axis)
				.withWeight(weight)
				.withHeight(height)
				.withNoisePollution(noisePollution);
		Vehicle vehicle = builder.build();
		store(vehicle);
		
		return vehicle;
	}
	
	public static void store(Vehicle vehicle) {
		boolean type = vehicle instanceof VehicleHeavy;
		try {
			PreparedStatement ps = JDBC.connect().prepareStatement("INSERT INTO Vehicle values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, vehicle.getPlateNumber());
			ps.setString(2, vehicle.getBrand());
			ps.setString(3, vehicle.getModel());
			ps.setBoolean(4, type);
			ps.setString(5, Character.toString(vehicle.getUnitRate()));
			ps.setInt(6, vehicle.getYear());
			ps.setInt(7, vehicle.getAxis());
			ps.setInt(8, vehicle.getHeight());
			ps.setInt(9, vehicle.getWeight());
			ps.setInt(10, vehicle.getNoisePollution());
			ps.setInt(11, vehicle.getEnvironmentalClass());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void update(Vehicle vehicle) {

		try {
			PreparedStatement ps = JDBC.connect().prepareStatement("UPDATE Vehicle SET plate_number=?, brand=?, model=?, unitRate=?, year=?, axis=?, height=?, weight=?, noise_pollution=?, environmental_class=? WHERE plate_number=?");
			ps.setString(1, vehicle.getPlateNumber());
			ps.setString(2, vehicle.getBrand());
			ps.setString(3, vehicle.getModel());
			ps.setString(4, Character.toString(vehicle.getUnitRate()));
			ps.setInt(5, vehicle.getYear());
			ps.setInt(6, vehicle.getAxis());
			ps.setInt(7, vehicle.getHeight());
			ps.setInt(8, vehicle.getWeight());
			ps.setInt(9, vehicle.getNoisePollution());
			ps.setInt(10, vehicle.getEnvironmentalClass());
			ps.setString(12, vehicle.getPlateNumber());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Vehicle show(String plateNumber) { 
		Vehicle vehicle = null;
		try {
			Statement statement = JDBC.connect().createStatement();
			ResultSet resultSet = statement.executeQuery( "SELECT * FROM vehicle WHERE plate_number=" + plateNumber );
			while(resultSet.next() ) {
				vehicle = new Vehicle.BuildVehicle(resultSet.getString("plate_number"))
						.withBrandModel(resultSet.getString("model"), resultSet.getString("brand"))
						.withYear(resultSet.getInt("year"))
						.withAxis(resultSet.getInt("axis"))
						.withWeight(resultSet.getInt("weight"))
						.withHeight(resultSet.getInt("height"))
						.build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vehicle;
	}
	
	public static void delete(String plateNumber) { 
		try {
			JDBC.connect().prepareStatement("DELETE FROM Vehicle WHERE plate_number='" + plateNumber + "';").execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static LinkedList<Toll> tollHistory(String plateNumber) {
		
		Motorway motorway = Motorway.getInstance();
		LinkedList<Toll> history = new LinkedList<Toll>();
		try {
			Statement statement = JDBC.connect().createStatement();
			ResultSet resultSet = statement.executeQuery( "SELECT * FROM toll JOIN Tollbooth AS i ON Toll.in=Tollbooth.id JOIN Tollbooth AS o ON Toll.out=Tollbooth.id WHERE plate_number=" + plateNumber );
			while(resultSet.next() ) {
				history.add(new Toll(resultSet.getInt("toll.id"), plateNumber, resultSet.getFloat("cost"), new Tollbooth(resultSet.getString("i.name"), resultSet.getInt("i.km")), new Tollbooth(resultSet.getString("o.name"), resultSet.getInt("o.km"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return history; 
		
	}
	
}

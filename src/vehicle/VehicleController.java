/**
 * 
 */
package vehicle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;

import helper.JDBC;
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
			System.out.println(vehicle.getUnitRate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
		boolean type = vehicle instanceof VehicleHeavy;
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
	
	public static <T> LinkedList<T> tollHistory(String plateNumber) { //return ECOToll or related toll
		LinkedList<T> history = new LinkedList<T>();
		
		//TODO
		
		return history; 
	}
	
}

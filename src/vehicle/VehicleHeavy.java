/**
 * 
 */
package vehicle;

/**
 * @author alessandrodorazio
 *
 */
class VehicleHeavy extends Vehicle {

	public VehicleHeavy() {
		super();
	}
	
	//constructor 2019
	public VehicleHeavy(String brand, String model, String plateNumber, int year, int axis, int weight, int height) {
		super(brand, model, plateNumber, year, axis, weight, height);
		// TODO Auto-generated constructor stub
	}
	
	//constructor 2021
	public VehicleHeavy(String brand, String model, String plateNumber, char environmentalClass, int year, int axis,
			int weight, int height, int noisePollution) {
		super(brand, model, plateNumber, environmentalClass, year, axis, weight, height, noisePollution);
		// TODO Auto-generated constructor stub
	}

}

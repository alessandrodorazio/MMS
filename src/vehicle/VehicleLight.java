package vehicle;

class VehicleLight extends Vehicle {
	
	public VehicleLight() {
		
	}

	//constructor 2019
	public VehicleLight(String brand, String model, String plateNumber, int year, int axis, int weight, int height) {
		super(brand, model, plateNumber, year, axis, weight, height);
		// TODO Auto-generated constructor stub
	}

	//constructor 2026
	public VehicleLight(String brand, String model, String plateNumber, char environmentalClass, int year, int axis,
			int weight, int height, int noisePollution) {
		super(brand, model, plateNumber, environmentalClass, year, axis, weight, height, noisePollution);
		// TODO Auto-generated constructor stub
	}

}

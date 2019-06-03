/**
 * 
 */
package vehicle;

import helper.Costant;

/**
 * @author alessandrodorazio
 *
 */
public abstract class Vehicle {
	
	private String brand, model, plateNumber;
	private char unitRate; //unitRate = AB345
	private int year, axis, weight, height, environmentalClass, noisePollution;
	
	public static class BuildVehicle {
		
		private String brand, model, plateNumber;
		private int year, axis, weight, height, environmentalClass = 0, noisePollution = 0;
				
		public BuildVehicle(String plateNumber) {
			this.plateNumber = plateNumber;
			this.environmentalClass = 1;
			this.noisePollution = 0;
		}
		
		public BuildVehicle withBrandModel(String model, String brand) {
			this.model = model;
			this.brand = brand;
			return this;
		}
		
		public BuildVehicle withEnvironmentalClass(int environmentalClass) {
			this.environmentalClass = environmentalClass;
			return this;
		}
		
		public BuildVehicle withYear(int year) {
			this.year = year;
			return this;
		}
		
		public BuildVehicle withAxis(int axis) {
			this.axis = axis;
			return this;
		}
		
		public BuildVehicle withWeight(int weight) {
			this.weight = weight;
			return this;
		}
		
		public BuildVehicle withHeight(int height) {
			this.height = height;
			return this;
		}
		
		public BuildVehicle withNoisePollution(int noisePollution) {
			this.noisePollution = noisePollution;
			return this;
		}
		
		public Vehicle build() {
			Vehicle v;
			if(axis <= 2 && height <= 130) v = new VehicleLight(); else v = new VehicleHeavy();
			//check parameters or return exception
			v.brand = this.brand;
			v.model = this.model;
			v.plateNumber = this.plateNumber;
			if(this.environmentalClass > 0 && this.environmentalClass <= Costant.EURO_MAX) v.environmentalClass = this.environmentalClass;
			v.year = this.year;
			v.axis = this.axis;
			v.weight = this.weight;
			v.height = this.height;
			v.setUnitRate(); //check domain
			if(this.noisePollution != 0) v.noisePollution = this.noisePollution;
			return v;
		}
		
	}
	
	
	protected Vehicle() { }
	
	//before reform
	Vehicle(String brand, String model, String plateNumber, int year, int axis, int weight, int height) {
		super();
		this.brand = brand;
		this.model = model;
		this.plateNumber = plateNumber;
		this.year = year;
		this.axis = axis;
		this.weight = weight;
		this.height = height;
		
		this.setUnitRate();
	}
	
	//after reform
	Vehicle(String brand, String model, String plateNumber, char environmentalClass, int year, int axis, int weight,
			int height, int noisePollution) {
		super();
		this.brand = brand;
		this.model = model;
		this.plateNumber = plateNumber;
		this.environmentalClass = environmentalClass;
		this.year = year;
		this.axis = axis;
		this.weight = weight;
		this.height = height;
		this.noisePollution = noisePollution;
		
		this.setUnitRate();
	}
	
	protected String getBrand() { return brand;}
	protected void setBrand(String brand) { this.brand = brand; }
	
	protected String getModel() { return model; }
	protected void setModel(String model) { this.model = model; }
	
	protected String getPlateNumber() { return plateNumber; }
	protected void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
	
	protected int getEnvironmentalClass() { return environmentalClass; }
	protected void setEnvironmentalClass(int environmentalClass) { this.environmentalClass = environmentalClass; }
	
	protected int getYear() { return year; }
	protected void setYear(int year) { this.year = year; }
	
	protected int getAxis() { return axis; }
	protected void setAxis(int axis) { this.axis = axis; }
	
	protected int getWeight() { return weight; }
	protected void setWeight(int weight) { this.weight = weight; }
	
	protected int getHeight() { return height; }
	protected void setHeight(int height) { this.height = height; }
	
	protected int getNoisePollution() { return noisePollution; }
	protected void setNoisePollution(int noisePollution) { this.noisePollution = noisePollution;}
	
	public char getUnitRate() { return unitRate; }
	protected void setUnitRate() {
		int axis = (this.axis>5)?5:this.axis;
        unitRate = (axis==2 && this.height < 130)?'A':((axis==2)?'B':(char)(axis+'0'));
	}
	public int getUnitRateInt() {
		if(unitRate == 'A') return 1;
		if(unitRate == 'B') return 2;
		return unitRate;
	}
	
}

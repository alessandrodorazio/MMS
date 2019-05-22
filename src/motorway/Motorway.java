/**
 * 
 */
package motorway;

import vehicle.Vehicle;

/**
 * @author alessandrodorazio
 *
 */

public final class Motorway {

	private static Motorway motorway = new Motorway();
	
	private String name;
	private float[] unitRate;
	
	private Motorway() {
		//TODO
		//find on db, if not found create
		this.name = "Nome autostrada";
		this.unitRate = new float[] {0f};
		//insert into db
	}

	public String getName() { return motorway.name; }
	protected void setName(String name) { motorway.name = name; }

	public float[] getUnitRate() { return motorway.unitRate; }
	protected void setUnitRate(float[] unitRate) { motorway.unitRate = unitRate; }
	public float getUnitRateSingle(Vehicle v) { return motorway.unitRate[v.getUnitRateInt() - 1]; }
	
	public static final Motorway getInstance() { return motorway; }
	
}

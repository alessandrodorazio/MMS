/**
 * 
 */
package motorway;

import helper.Helper;
import tollbooth.Tollbooth;
import vehicle.Vehicle;

/**
 * @author alessandrodorazio
 *
 */
public class RegularToll implements Toll {
	
	int id;
	String vehicle;
	float cost;
	Tollbooth in, out;

	RegularToll(int id, String vehicle, float cost, Tollbooth in, Tollbooth out) {
		super();
		this.id = id;
		this.vehicle = vehicle;
		this.cost = cost;
		this.in = in;
		this.out = out;
		//send data
	}

	@Override
	public float tollCalc(Tollbooth in, Tollbooth out, Vehicle v) {
		float km = routeCalc(in, out);
        if(km == 0) throw new Error("I due caselli coincidono"); //throw exception
        float cost = km * Motorway.getInstance().getUnitRateSingle(v) * (1 + Helper.IVA); //calcolo del pedaggio
        return Helper.round(cost); //arrotondiamo tramite la classe Helper
	}

	@Override
	public int routeCalc(Tollbooth in, Tollbooth out) {
		return (in.getKm() > out.getKm())?(in.getKm() - out.getKm()):(out.getKm() - in.getKm());
	}

}

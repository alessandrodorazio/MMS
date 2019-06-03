/**
 * 
 */
package motorway;

import helper.Helper;
import helper.Costant;
import tollbooth.Tollbooth;
import vehicle.Vehicle;

/**
 * @author alessandrodorazio
 *
 */
public class Toll {
	
	int id;
	String vehicle;
	float cost;
	Tollbooth in, out;

	public Toll(int id, String vehicle, float cost, Tollbooth in, Tollbooth out) {
		this.id = id;
		this.vehicle = vehicle;
		this.cost = cost;
		this.in = in;
		this.out = out;
		//send data
	}

	public static float tollCalc(Tollbooth in, Tollbooth out, Vehicle v) {
		float km = routeCalc(in, out);
        if(km == 0) throw new Error("I due caselli coincidono"); //throw exception
        float cost = km * Motorway.getInstance().getUnitRateSingle(v) * (1 + Costant.IVA);
        return Helper.round(cost);
	}

	public static int routeCalc(Tollbooth in, Tollbooth out) {
		return (in.getKm() > out.getKm())?(in.getKm() - out.getKm()):(out.getKm() - in.getKm());
	}

	
}
